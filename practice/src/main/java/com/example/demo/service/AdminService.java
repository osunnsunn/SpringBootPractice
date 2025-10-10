package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminSigninForm;
import com.example.demo.form.AdminSignupForm;

public interface AdminService {
	void registerAdmin(AdminSignupForm form);

	Admin login(AdminSigninForm form);
}