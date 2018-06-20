package edu.hm.cs.cieserver.department;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import edu.hm.cs.cieserver.course.Course;
import org.junit.Test;

public class DepartmentTest {

	@Test
	public void validatePOJO() {
		PojoClass pojo = PojoClassFactory.getPojoClass(Department.class);

		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule()).with(new GetterMustExistRule())
				.with(new SetterTester()).with(new GetterTester()).build();

		validator.validate(pojo);
	}

}
