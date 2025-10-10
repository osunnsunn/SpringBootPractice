package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.AdminSignupForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminSignupController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	@GetMapping("/admin/signup")
	public String signupForm(Model model) {
		model.addAttribute("adminSignupForm", new AdminSignupForm());
		return "adminSignup";
	}

	@PostMapping("/admin/signup")
	public String registerAdmin(@Validated @ModelAttribute("adminSignupForm") AdminSignupForm form,
			BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "adminSignup";
		}
		
		String encodedPassword = passwordEncoder.encode(form.getPassword());
        form.setPassword(encodedPassword);

		adminService.registerAdmin(form);
		model.addAttribute("message", "管理者登録が完了しました！");
		return "adminSignup";
	}
}
