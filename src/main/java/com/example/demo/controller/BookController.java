package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entiry.Book;
import com.example.demo.service.BookService;


@Controller
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	BookService bookService;

	@RequestMapping("/")
	public String index() {
		return "/book/index";
	}
	
	@RequestMapping("/detail/{bookid}")
	public String detail(@PathVariable("bookid") String bookId, Model model) {
		// TODO 入力値のバリデーションチェックが行いたい。
	    Book book = bookService.getOne(Integer.valueOf(bookId));
	    model.addAttribute("book", book);
		return "/book/detail";
	}
	
	@RequestMapping("/regist/entry")
	public String regist(Model model) {
		Book book = new Book();
		model.addAttribute("title", "登録");
		// Bookオブジェクトを設定しないと、タイムリーフ側で落ちてしまう。
		// 登録・更新画面を共用しており、登録時だけ下記未設定というのは不可。
		model.addAttribute("book", book);
		model.addAttribute("proc", "regist");
		return "book/registorupdate";
	}
	
	@RequestMapping("/regist/confirm")
	public String confirmForRegist(@Validated Book book, BindingResult result
			, Model model, @RequestParam("title") String title) {
		if (result.hasErrors()) {
			return "book/registorupdate";
		}
	    model.addAttribute("book", book);
	    model.addAttribute("title", title);
		return "book/confirm";
	}
	
	@RequestMapping("/update/{bookid}")
	public String update(@PathVariable("bookid") String bookId, Model model) {
	    Book book = bookService.getOne(Integer.valueOf(bookId));
	    model.addAttribute("book", book);
	    model.addAttribute("title", "修正");
		model.addAttribute("proc", "update");
		return "book/registorupdate";
	}
	
	@RequestMapping("/update/confirm")
	public String confirm(@Validated Book book, BindingResult result
			, Model model, @RequestParam("title") String title) {
		if (result.hasErrors()) {
			return "book/registorupdate";
		}
	    model.addAttribute("book", book);
	    model.addAttribute("title", title);
		model.addAttribute("proc", "update");
		return "book/confirm";
	}
	
	@PostMapping("/update/complete")
	public String updateBookInfo(@Validated Book book, BindingResult result
			,Model model, @RequestParam("title") String title) {
		if (result.hasErrors()) {
			return "book/registorupdate";
		}
		
		bookService.saveAndFlush(book);
		
	    model.addAttribute("book", book);
	    model.addAttribute("title", title);
		return "book/complete";
	}
	
	@GetMapping("/update/complete")
	public String updateBookInfo() {
		return "/book/search";
	}

	@RequestMapping("/search")
	public String search(Model model) {
		List<Book> list = bookService.findAll();
		model.addAttribute("booklist", list);
		return "/book/search";
	}

	/**
	 * API風味にしてBook情報を返却してみた。
	 * @return
	 */
	@RequestMapping("/api/selectall")
	@ResponseBody
	public String apiSelectAll() {
		StringBuilder sb = new StringBuilder();
		
		List<Book> list = bookService.findAll();
		for (Book book : list) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append("[").append(book).append("]");
		}

		return sb.toString();

	}
}
