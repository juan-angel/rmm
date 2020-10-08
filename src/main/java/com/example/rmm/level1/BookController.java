package com.example.rmm.level1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.Book;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private List<Book> bookList;
	
	@PostMapping("/lendings/{idBook}")
	public Book findBooks(@PathVariable("idBook") Integer id) {
		Book result = null;
		
		if (id != null) {
			result = bookList.stream()
						.filter(b -> b.isAvailable() && b.getId() == id)
						.findFirst().orElse(null);
			
			if (result != null) {
				result.setAvailable(false);
			}
		}
		
		return result;
	}
}
