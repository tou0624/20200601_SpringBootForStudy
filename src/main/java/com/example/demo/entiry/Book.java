package com.example.demo.entiry;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="book_id")
	private int id;

	@NotNull
	@Size(min=1, max=100)
	@Column(name="book_name")
	private String bookName;

	@NotNull
	@Size(min=1, max=100)
	@Column(name="book_exp")
	private String bookExp;

	@NotNull
	@Column(name="rate")
	private int rate;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
			.append("book_id=").append(id)
			.append(",book_name=").append(bookName)
			.append(",book_exp=").append(bookExp)
			.append(",rate=").append(rate);
		return sb.toString();
	}

}
