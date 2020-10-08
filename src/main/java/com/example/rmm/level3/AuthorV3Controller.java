package com.example.rmm.level3;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.BookV3;

@RestController
@RequestMapping("/authors-v3")
public class AuthorV3Controller {
	@Autowired
	private List<BookV3> bookList;

	@GetMapping("/books")
	public ResponseEntity<List<BookV3>> findAuthorBooks(@RequestParam("authorName") String authorName) {
		List<BookV3> authorBooks = null;
		ResponseEntity<List<BookV3>> result = null;

		if (!StringUtils.isEmpty(authorName)) {
			authorBooks = bookList.stream().filter(b -> b.getAuthor().toUpperCase().contains(authorName.toUpperCase()))
					.collect(Collectors.toList());
		}

		if (CollectionUtils.isEmpty(authorBooks)) {
			result = ResponseEntity.notFound().build();
		} else {
			authorBooks.stream().forEach(this::addLinkInformation);
			result = ResponseEntity.ok(authorBooks);
		}

		return result;
	}
	
	private void addLinkInformation(BookV3 book) {
		book.removeLinks();	// Remove previous links just in case they're exist
		book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookV3Controller.class).lendBook(book.getId())).withRel("lending"));
	}
}
