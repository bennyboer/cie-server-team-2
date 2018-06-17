package edu.hm.cs.cieserver.course;

import edu.hm.cs.cieserver.course.date.CourseAppointmentRepository;
import edu.hm.cs.cieserver.department.Department;
import edu.hm.cs.cieserver.department.DepartmentRepository;
import edu.hm.cs.cieserver.lecturer.Lecturer;
import edu.hm.cs.cieserver.lecturer.LecturerRepository;
import edu.hm.cs.cieserver.user.User;
import edu.hm.cs.cieserver.user.UserDetailsServiceImpl;
import edu.hm.cs.cieserver.user.UserRepository;
import org.apache.commons.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;

/**
 * Controller handling course purposes.
 */
@RestController
@RequestMapping({"/api/courses"})
public class CourseController {

	private static String[] PDF_HEADERS = new String[] {
			"Courses in English",
			"Course Description",
			"Department",
			"Course title",
			"Hours per week (SWS)",
			"Number of ECTS credits",
			"Course objective",
			"Prerequisites",
			"Recommended reading",
			"Teaching methods",
			"Assessment methods",
			"Language of instruction",
			"Name of lecturer",
			"Email",
			"Link",
			"Course content",
			"Remarks"
	};

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private LecturerRepository lecturerRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CourseAppointmentRepository courseAppointmentRepository;

	@PostMapping(path = "/import/xml")
	public void importCoursesFromXML(@RequestBody String xmlUrl) throws IOException, ParserConfigurationException, SAXException {
		String rawXML = IOUtils.toString(new URL(xmlUrl).openStream(), StandardCharsets.UTF_8);
		rawXML = rawXML.replaceAll("&", "&amp;");

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(IOUtils.toInputStream(rawXML, "UTF-8"));

		doc.getDocumentElement().normalize();

		NodeList rows = doc.getElementsByTagName("row");

		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);

			if (row.getNodeType() == Node.ELEMENT_NODE) {
				Element rowElm = (Element) row;

				if (!rowElm.hasAttribute("header")) {
					// Is course element.
					NodeList list = rowElm.getElementsByTagName("column");

					Node course = list.item(0);

					if (course.getNodeType() == Node.ELEMENT_NODE && course.getChildNodes().getLength() >= 2) {
						Element courseElm = (Element) course;
						String pdfUrl = courseElm.getElementsByTagName("url").item(0).getTextContent();
						String title = courseElm.getElementsByTagName("text").item(0).getTextContent();

						String lecturer = list.item(1).getTextContent();

						double sws;
						double ects;
						double usCredits;
						try {
							sws = Double.parseDouble(list.item(2).getTextContent().replace(',', '.'));
							ects = Double.parseDouble(list.item(3).getTextContent().replace(',', '.'));
							usCredits =  Double.parseDouble(list.item(4).getTextContent().replace(',', '.'));
						} catch (Exception e) {
							continue;
						}

						String category = list.item(5).getTextContent();
						String level = list.item(6).getTextContent();

						// Parse PDF
						Map<String, StringBuilder> additionalInfo = new HashMap<>();
						if (pdfUrl != null && !pdfUrl.isEmpty()) {
							PDDocument document = PDDocument.load(new URL(pdfUrl).openStream());

							PDFTextStripperByArea stripper = new PDFTextStripperByArea();
							stripper.setSortByPosition(true);

							PDFTextStripper tStripper = new PDFTextStripper();

							String pdfFileInText = tStripper.getText(document);

							// Split by whitespace
							String lines[] = pdfFileInText.split("\\r?\\n");

							int headerIndex = 0;
							String nextHeader = PDF_HEADERS[headerIndex];
							String currentHeader = null;

							for (String line : lines) {
								if (nextHeader != null && line.startsWith(nextHeader)) {
									currentHeader = nextHeader;

									// New entry.
									StringBuilder sb = new StringBuilder();
									sb.append(line.substring(currentHeader.length()));

									additionalInfo.put(currentHeader, sb);

									if (PDF_HEADERS.length > headerIndex + 1) {
										nextHeader = PDF_HEADERS[++headerIndex];
									} else {
										nextHeader = null;
									}
								} else if (currentHeader != null) {
									// Append to last entry.
									StringBuilder sb = additionalInfo.get(currentHeader);

									sb.append(line);
								}
							}
						}

						// Create new course.
						if (title != null && !title.isEmpty() && courseRepository.findByName(title).isEmpty()) {
							Course newCourse = new Course();
							newCourse.setName(title);
							newCourse.setSemesterWeekHours(sws);
							newCourse.setEcts(ects);
							newCourse.setUsCredits(usCredits);
							newCourse.setCourseStatus(CourseStatus.valueOf(category.toUpperCase()));

							// Try to setup a lecturer.
							Lecturer l = null;
							if (lecturer != null && !lecturer.isEmpty()) {
								List<Lecturer> lecturers = lecturerRepository.findByName(lecturer);

								if (lecturers.isEmpty()) {
									l = new Lecturer();
									l.setName(lecturer);

									StringBuilder sb = additionalInfo.get("Email");
									if (sb != null) {
										l.setEmail(sb.toString().trim());
									}

									lecturerRepository.save(l);
								} else {
									l = lecturers.get(0);
								}
							}
							newCourse.setLecturer(l);

							// Try to setup a department
							Department d = null;
							StringBuilder departmentSb = additionalInfo.get("Department");
							if (departmentSb != null) {
								String department = departmentSb.toString().trim();

								try {
									int departmentNumber = Integer.parseInt(department);

									Optional<Department> departmentOptional = departmentRepository.findById((long) departmentNumber);

									if (departmentOptional.isPresent()) {
										d = departmentOptional.get();
									} else {
										d = new Department();
										d.setNumber((long) departmentNumber);
										d.setName("");
										d.setColor(0xFFFFFF);
									}
								} catch (Exception e) {

								}
							}
							newCourse.setDepartment(d);

							// Add additional info (if available) to the course.
							StringBuilder sb = additionalInfo.get("Course content");
							if (sb != null) {
								newCourse.setDescription(sb.toString().trim());
							}

							courseRepository.save(newCourse);
						}
					}
				}
			}
		}
	}

	@GetMapping(path = "/selected/{userId}")
	public Set<Course> findSelectedCoursesByUserId(@PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get().getSelectedCourses();
		}

		return Collections.emptySet();
	}

	@GetMapping(path = "/selected")
	public Set<Course> findSelectedCoursesForLoggedOnUser(Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		return user.getSelectedCourses() != null ? user.getSelectedCourses() : Collections.emptySet();
	}

	@GetMapping(path = "/favorized/{userId}")
	public Set<Course> findFavorizedCoursesByUserId(@PathVariable("userId") Long userId) {
		Optional<User> user = userRepository.findById(userId);

		if (user.isPresent()) {
			return user.get().getFavorizedCourses();
		}

		return Collections.emptySet();
	}

	@GetMapping(path = "/favorized")
	public Set<Course> findFavorizedCoursesForLoggedOnUser(Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		return user.getFavorizedCourses() != null ? user.getFavorizedCourses() : Collections.emptySet();
	}

	@GetMapping(path = {"/{id}"})
	public Course findOne(@PathVariable("id") Long id) {
		return courseRepository.findById(id).get();
	}

	@GetMapping
	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	@PostMapping
	public Course create(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@PostMapping(path = "/select/{courseId}")
	public boolean select(@PathVariable("courseId") Long courseId, Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			user.getSelectedCourses().add(course.get());
			userRepository.save(user);

			return true;
		}

		return false;
	}

	@PostMapping(path = "/deselect/{courseId}")
	public boolean deselect(@PathVariable("courseId") Long courseId, Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			user.getSelectedCourses().remove(course.get());
			userRepository.save(user);

			return true;
		}

		return false;
	}

	@PostMapping(path = "/unfavorize/{courseId}")
	public boolean unfavorize(@PathVariable("courseId") Long courseId, Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			user.getFavorizedCourses().remove(course.get());
			userRepository.save(user);

			return true;
		}

		return false;
	}

	@PostMapping(path = "/favorize/{courseId}")
	public boolean favorize(@PathVariable("courseId") Long courseId, Principal principal) {
		User user = (User) userDetailsService.loadUserByUsername(principal.getName());

		Optional<Course> course = courseRepository.findById(courseId);

		if (course.isPresent()) {
			user.getFavorizedCourses().add(course.get());
			userRepository.save(user);

			return true;
		}

		return false;
	}

	@PutMapping
	public Course update(@RequestBody Course course) {
		return courseRepository.save(course);
	}

	@DeleteMapping(path = {"/{id}"})
	public Course delete(@PathVariable("id") Long id) {
		Optional<Course> course = courseRepository.findById(id);

		course.ifPresent(u -> courseRepository.delete(u));

		return course.get();
	}

	@DeleteMapping(path = {"/delete-selections"})
	public boolean deleteCourseSelections() {
		List<User> users = userRepository.findAll();

		for (User user : users) {
			user.getSelectedCourses().clear();
			userRepository.save(user);
		}

		return true;
	}

	@DeleteMapping(path = {"/delete-favorites"})
	public boolean deleteCourseFavorites() {
		List<User> users = userRepository.findAll();

		for (User user : users) {
			user.getFavorizedCourses().clear();
			userRepository.save(user);
		}

		return true;
	}

}
