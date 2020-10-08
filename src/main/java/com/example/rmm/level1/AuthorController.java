package com.example.rmm.level1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.Book;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private List<Book> bookList;
	
	@PostMapping("/books")
	public List<Book> findBooks(@RequestBody String authorName) {
		List<Book> result = null;
		
		if (!StringUtils.isEmpty(authorName)) {
			result = bookList.stream()
						.filter(b -> b.getAuthor().toUpperCase().contains(authorName.toUpperCase()))
						.collect(Collectors.toList());
		}
		
		return result;
	}
}
