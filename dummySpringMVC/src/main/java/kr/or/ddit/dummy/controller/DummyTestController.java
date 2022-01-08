package kr.or.ddit.dummy.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.TestVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dummy/test")
@Slf4j
public class DummyTestController {
	
	@ModelAttribute("dataList")
	public List<Integer> getDataList(){
		return Arrays.asList(1,2,3);
	}

	@RequestMapping(method=RequestMethod.GET)
	public String form() {
		return "dummy/testForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String process(@ModelAttribute("test") TestVO testVO) { // command object
		log.info("전송 데이터 command object 로 받음. : {}", testVO);
		return "redirect:/dummy/test";
	}
}















