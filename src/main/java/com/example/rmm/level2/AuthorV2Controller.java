package com.example.rmm.level2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.Book;

@RestController
@RequestMapping("/authors-v2")
public class AuthorV2Controller {
	@Autowired
	private List<Book> bookList;
	
	@GetMapping("/books")
	public ResponseEntity<List<Book>> findAuthorBooks(@RequestParam("authorName") String authorName) {
		List<Book> result = null;
		
		if (!StringUtils.isEmpty(authorName)) {
			result = bookList.stream()
						.filter(b -> b.getAuthor().toUpperCase().contains(authorName.toUpperCase()))
						.collect(Collectors.toList());
		}
		
		return CollectionUtils.isEmpty(result) ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}
}
