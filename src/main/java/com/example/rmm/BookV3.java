package com.example.rmm;

import org.springframework.hateoas.RepresentationModel;

public class BookV3 extends RepresentationModel<BookV3> {
	private Integer id;
	private String title;
	private String author;
	private boolean available;

	public BookV3() {
		// TODO Auto-generated constructor stub
	}

	public BookV3(Integer id, String author, String title, boolean available) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.available = available;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}
