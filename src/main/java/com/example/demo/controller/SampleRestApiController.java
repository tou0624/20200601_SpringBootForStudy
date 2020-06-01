package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestContollerの動作確認用（親パス付与ver）
 * @author sato
 */
@RestController
@RequestMapping("/api")
public class SampleRestApiController {

	@RequestMapping("/userinfo")
	public String testJson() {
		return "{name:\"佐藤\"}";
	}

}
