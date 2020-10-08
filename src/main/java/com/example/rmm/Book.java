package com.example.rmm;

public class Book {
	private int id;
	private String author;
	private String title;
	private boolean available;

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(int id, String author, String title, boolean available) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
