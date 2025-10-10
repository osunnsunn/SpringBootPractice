package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminSigninController {

	@GetMapping("/admin/signin")
	public String login() {
	    return "adminSignin";
	}

}