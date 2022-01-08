package kr.or.ddit.user.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.admin.service.AdminServiceImpl;

@Controller
@RequestMapping("/user")
public class UserIndexController {
	@Inject
	private AdminServiceImpl service;
	
	@RequestMapping("**")
	public String index(Model model) {
		model.addAttribute("data", service.readLegacyData());
		return "userView";
	}
}
