package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 通常のコントローラ
 * @author sato
 */
@Controller
public class SampleController {

	@RequestMapping(value="/hello")
	public String hello() {
		return "hello/index.html";
	}
	
	@RequestMapping("/hello2")
	public String hello2() {
		// ビュー名の拡張子なしでも自動的に検知してくれる。
		return "hello/index2";
	}

	@RequestMapping(
			value = "/hello3"
			, method = RequestMethod.GET)
	public String hello3_else() {
		return "hello/index4";
	}

	// 上記と同じかつ絞り込みパラメータ条件が厳しいのだが、こちらにきっちり遷移。
	// 書き順は関係ないみたい。
	@RequestMapping(
			value = "/hello3"
			, method = RequestMethod.GET
			, params = "id=001")
	public String hello3() {
		return "hello/index3";
	}
	
	@RequestMapping("/hello4")
	@ResponseBody
	public String hello4(){
		return "hello4へようこそ！";
	}

	@RequestMapping("/hello5")
	public ModelAndView hello5(ModelAndView mav) {
		// ModelAndViewを使う事で、ビューとモデルを一緒に返却可能らしい。
		mav.addObject("model", "model");
		mav.setViewName("hello/index5");
		return mav;
	}
	
	@RequestMapping("/hello6/{userId}")
	public ModelAndView hello6(@PathVariable("userId") String userId, ModelAndView mav) {
		if (userId != null && !"".equals(userId)) {
			mav.setViewName("hello/index61");
		} else {
			// こちらの分岐に入る事はない。その前のとこでエラーになるから。
			mav.setViewName("hello/index62");
		}
		return mav;
	}

	@RequestMapping("/hello6")
	public ModelAndView hello6(ModelAndView mav) {
		mav.setViewName("hello/index63");
		return mav;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping(value="/result", method=RequestMethod.POST)
	public ModelAndView send(@RequestParam("inputvalue")String inputvalue, ModelAndView mav) {
		mav.setViewName("result");
		mav.addObject("message", inputvalue);
		return mav;
	}
	
}
