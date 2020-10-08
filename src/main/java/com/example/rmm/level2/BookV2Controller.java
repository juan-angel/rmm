package com.example.rmm.level2;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.Book;

@RestController
@RequestMapping("/books-v2")
public class BookV2Controller {
	@Autowired
	private List<Book> bookList;
	
	@PostMapping("/lendings")
	public ResponseEntity<Book> lendBook(@RequestBody Integer id) {
		ResponseEntity<Book> result = null;
		if (id == null) {
			result = ResponseEntity.badRequest().build();
		} else {
			Book book = bookList.stream()
						.filter(b -> b.getId() == id)
						.findFirst().orElse(null);
			
			if (book == null) {
				result = ResponseEntity.notFound().build();
			} else if (!book.isAvailable()) {
				result = ResponseEntity.status(HttpStatus.CONFLICT).body(book);
			} else {
				book.setAvailable(false);
				result = ResponseEntity.created(URI.create("/books-v2/lendings/" + book.getId())).body(book);
			}
		}
		
		return result;
	}
	
	@GetMapping("/lendings/{idBook}")
	public ResponseEntity<Book> searchLending(@PathVariable("idBook") Integer id) {
		ResponseEntity<Book> result = null;
		
		if (id == null) {
			result = ResponseEntity.badRequest().build();
		} else {
			Book book = bookList.stream().filter(b -> !b.isAvailable() && b.getId() == id).findFirst().orElse(null);
			
			if (book == null) {
				result = ResponseEntity.notFound().build();
			} else {
				result = ResponseEntity.ok(book);
			}
		}
		
		return result;
	}
}
