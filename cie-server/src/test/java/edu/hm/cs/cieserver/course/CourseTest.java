package edu.hm.cs.cieserver.course;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import edu.hm.cs.cieserver.campus.Campus;
import edu.hm.cs.cieserver.course.date.CourseAppointment;
import org.junit.Test;

public class CourseTest {

	@Test
	public void validatePOJO() {
		validateClass(Course.class);
		validateClass(CourseAppointment.class);
	}

	private void validateClass(Class<?> c) {
		PojoClass pojo = PojoClassFactory.getPojoClass(c);

		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule()).with(new GetterMustExistRule())
				.with(new SetterTester()).with(new GetterTester()).build();

		validator.validate(pojo);
	}

}
