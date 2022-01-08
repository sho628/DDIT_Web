package kr.or.ddit.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/common")
public class CommonIndexController {
	
	@RequestMapping("**")
	public String index() {
		return "index";
	}
}
