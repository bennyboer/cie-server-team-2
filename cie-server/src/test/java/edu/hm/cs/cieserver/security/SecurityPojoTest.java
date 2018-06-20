package edu.hm.cs.cieserver.security;

import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import edu.hm.cs.cieserver.security.login.JWTAuthenticationResponse;
import edu.hm.cs.cieserver.security.login.LoginRequest;
import edu.hm.cs.cieserver.security.signup.SignUpRequest;
import org.junit.Test;

public class SecurityPojoTest {

	@Test
	public void validatePOJO() {
		validateClass(JWTAuthenticationResponse.class);
		validateClass(LoginRequest.class);
		validateClass(SignUpRequest.class);
	}

	private void validateClass(Class<?> c) {
		PojoClass pojo = PojoClassFactory.getPojoClass(c);

		Validator validator = ValidatorBuilder.create()
				.with(new SetterMustExistRule()).with(new GetterMustExistRule())
				.with(new SetterTester()).with(new GetterTester()).build();

		validator.validate(pojo);
	}

}
