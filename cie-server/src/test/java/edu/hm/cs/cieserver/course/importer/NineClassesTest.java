package edu.hm.cs.cieserver.course.importer;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

public class NineClassesTest {

	@Test
	public void NineClasses() {
		validateClass(NineCourse.class);
		validateClass(NineCourseAppointment.class);
		validateClass(NineLecturer.class);
		validateClass(NineRoom.class);
		validateClass(NineCorrelations.class);
	}

	private void validateClass(Class<?> c) {
		PojoClass pojo = PojoClassFactory.getPojoClass(c);

		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule()).with(new GetterMustExistRule())
				.with(new SetterTester()).with(new GetterTester()).build();

		validator.validate(pojo);
	}

}
