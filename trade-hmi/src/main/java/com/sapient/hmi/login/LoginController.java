package com.sapient.hmi.login;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

	@RequestMapping(method = RequestMethod.POST, value = "/authenticate")
	public @ResponseBody LoginResponse login(@RequestHeader(value="customerId") String userId,
			@RequestHeader(value="password") String password){
		return new LoginResponse(true, "GM", "EXPO", "");
	}

}
