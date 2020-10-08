package com.example.rmm.level0;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.Book;

@RestController
public class LibraryBookLendingController {
	private static final String ACT_GET_BOOKS = "getBooks";
	private static final String ACT_BORROW_BOOK = "borrowBook";
	
	@Autowired
	private List<Book> bookList;
	
	@PostMapping("/0/libraryBookLendingService")
	public Object endpoint(@RequestBody BookRequest request) {
		Object result = null;
		
		if (ACT_GET_BOOKS.equals(request.getAction())) {
			result = bookList;
		} else if (ACT_BORROW_BOOK.equals(request.getAction()) && request.getBookId() != null) {
			result = bookList.stream().filter(b -> b.isAvailable() && b.getId() == request.getBookId()).findFirst().orElse(null);
			
			if (result != null) {
				((Book) result).setAvailable(false);
			}
		}
		
		return result;
	}
}
