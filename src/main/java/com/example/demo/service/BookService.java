package com.example.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.entiry.Book;
import com.example.demo.repository.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	BookRepository repository;

	public List<Book> findAll() {
		// ここで指定するソートキーはEntity名。
		return repository.findAll(new Sort(Sort.Direction.ASC,"id"));
	}

	public Book getOne(int id) {
		return repository.getOne(id);
	}

	/**
	 * 保存。saveだとオートコミットoffとなる。こっちは即時コミット。
	 * @param book
	 * @return
	 */
	public Book saveAndFlush(Book book) {
		return repository.saveAndFlush(book);
	}

}
