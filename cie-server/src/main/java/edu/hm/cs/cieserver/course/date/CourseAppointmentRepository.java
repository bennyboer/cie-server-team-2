package edu.hm.cs.cieserver.course.date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseAppointmentRepository extends JpaRepository<CourseAppointment, Long> {
}
