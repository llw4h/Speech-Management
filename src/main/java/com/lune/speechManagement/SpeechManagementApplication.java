package com.lune.speechManagement;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.lune.speechManagement.model.Speech;
import com.lune.speechManagement.service.SpeechServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@Controller
public class SpeechManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpeechManagementApplication.class, args);
	}

	@Autowired
	private SpeechServiceImpl speechServiceImpl;

	@RequestMapping(path = {"/","/search"})
	public String searchAll(Speech speech,
							Model model,
							@RequestParam(value = "keyword", required = false) String keyword,
							@RequestParam(value = "startdate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startdate,
							@RequestParam(value = "enddate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate enddate) {
		if (startdate != null && enddate != null) { // if both dates have a value
			List<Speech> list = speechServiceImpl.getByKeywordAndDateRange(keyword, startdate, enddate);
			model.addAttribute("list", list);
		} else if (keyword != null && !keyword.isEmpty()) { // if keyword has a value
			List<Speech> list = speechServiceImpl.getByKeyword(keyword);
			model.addAttribute("list", list);

		} else { // display all speeches
			model.addAttribute("list", speechServiceImpl.getAllSpeech());
		}
		return "index.html";
	}

	@GetMapping("/addnew")
	public String addNewSpeech(Model model) {
		Speech speech = new Speech();
		model.addAttribute("speech", speech);
		return "newspeech.html";
	}

	@PostMapping("/save")
	public String saveSpeech(@ModelAttribute("speech") @DateTimeFormat(pattern = "yyyy-MM-dd") Speech speech) {
		speechServiceImpl.save(speech);
		return "redirect:/";
	}

	@GetMapping("/showFormForEdit/{id}")
	public String updateForm(@PathVariable(value = "id") Integer id, Model model) {
		Speech speech = speechServiceImpl.getById(id);
		model.addAttribute("speech", speech);
		return "update.html";
	}

	@GetMapping("/deleteSpeech/{id}")
	public String deleteThroughId(@PathVariable(value = "id") Integer id) {
		speechServiceImpl.deleteViaId(id);
		return "redirect:/";
	}

}
