package edu.hm.cs.cieserver.campus;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.Test;

public class CampusTest {

	@Test
	public void validatePOJO() {
		validateClass(Campus.class);
	}

	@Test
	public void validateCampusRequestPOJO() {
		validateClass(CampusRequest.class);
	}

	@Test
	public void validateLocationPOJO() {
		validateClass(Location.class);
	}

	private void validateClass(Class<?> c) {
		PojoClass pojo = PojoClassFactory.getPojoClass(c);

		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule()).with(new GetterMustExistRule())
				.with(new SetterTester()).with(new GetterTester()).build();

		validator.validate(pojo);
	}

}
