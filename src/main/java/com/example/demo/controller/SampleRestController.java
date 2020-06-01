package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestContollerの動作確認用
 * @author sato
 */
@RestController
public class SampleRestController {

	@RequestMapping("/test")
	public String testJson() {
		return "{test:true}";
	}

}
