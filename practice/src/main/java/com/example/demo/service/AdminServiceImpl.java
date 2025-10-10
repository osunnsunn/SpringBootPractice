package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminSignupForm;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public void registerAdmin(AdminSignupForm form) {
		Admin admin = new Admin();
		admin.setLastName(form.getLastName());
		admin.setFirstName(form.getFirstName());
		admin.setEmail(form.getEmail());
		admin.setPassword(passwordEncoder.encode(form.getPassword()));

		adminRepository.save(admin);
	}
}