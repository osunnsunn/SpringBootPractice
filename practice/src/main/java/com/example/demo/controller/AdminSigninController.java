package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.form.AdminSigninForm;
import com.example.demo.service.AdminService;

@Controller
public class AdminSigninController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/signin")
    public String showSigninPage(Model model) {
        model.addAttribute("adminSigninForm", new AdminSigninForm());
        return "adminSignin";
    }

    @PostMapping("/admin/signin")
    public String login(
            @Valid @ModelAttribute("adminSigninForm") AdminSigninForm form,
            BindingResult result,
            Model model) {
        
        if (result.hasErrors()) {
            return "adminSignin";
        }

        var admin = adminService.login(form);
        if (admin != null) {
            return "redirect:/admin/contacts";
        } else {
            model.addAttribute("message", "メールアドレスまたはパスワードが正しくありません。");
            return "adminSignin";
        }
    }
    
	@GetMapping("/contacts")
	@ResponseBody
	public String showContacts() {
		return "<h1>管理者用ページ</h1><p>ログイン成功です！</p>";
	}
    
}