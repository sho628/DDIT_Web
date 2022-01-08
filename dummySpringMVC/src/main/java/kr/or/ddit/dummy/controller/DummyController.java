package kr.or.ddit.dummy.controller;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.dummy.service.DummyService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/dummy")
@Slf4j
public class DummyController {
	private DummyService service;
	@Required
	@Inject
	public void setService(DummyService service) {
		this.service = service;
	}
	
	@PostConstruct
	public void init() {
		log.info("주입된 객체 : {}", service);
		log.info("주입된 컨테이너 주소 : {}", context.hashCode());
		log.info(" boardImages 주소 : {}", saveFolderURL);
		ServletContext application = context.getServletContext();
		saveFolder = new File(application.getRealPath(saveFolderURL));
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	@RequestMapping("first")
	public String dummy(Model model) {
		String info = service.retrieveData();
		model.addAttribute("info", info);
		return "dummy/view1";
	}
	
	@RequestMapping("second")
	public String form() {
		return "dummy/form";
	}
	
	@Inject
	private WebApplicationContext context;
	@Value("#{appInfo.boardImages}")
	private String saveFolderURL;
	private File saveFolder;
	
	@RequestMapping(value="second", method=RequestMethod.POST)
	public String process(String uploader, MultipartFile uploadFile, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		log.info("uploader : {}", uploader);
		log.info("upload File : {}", uploadFile.getOriginalFilename());
		uploadFile.transferTo(new File(saveFolder, uploadFile.getOriginalFilename()));
		String saveURL = saveFolderURL + "/" +uploadFile.getOriginalFilename();
		redirectAttributes.addFlashAttribute("saveURL", saveURL);
		return "redirect:/dummy/second";
	}
}























