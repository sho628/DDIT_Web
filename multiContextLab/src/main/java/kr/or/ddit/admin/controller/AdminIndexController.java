package kr.or.ddit.admin.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.user.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	@Inject
	private UserServiceImpl service;
	
	@RequestMapping("**")
	public String index(Model model) {
		model.addAttribute("data", service.readLegacyData());
		return "adminView";
	}
}
