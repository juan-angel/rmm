package com.example.rmm.level3;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rmm.BookV3;

@RestController
@RequestMapping("/books-v3")
public class BookV3Controller {
	@Autowired
	private List<BookV3> bookList;

	@PostMapping("/lendings")
	public ResponseEntity<BookV3> lendBook(@RequestBody Integer id) {
		ResponseEntity<BookV3> result = null;
		if (id == null) {
			result = ResponseEntity.badRequest().build();
		} else {
			BookV3 book = bookList.stream().filter(b -> b.getId() == id).findFirst().orElse(null);

			if (book == null) {
				result = ResponseEntity.notFound().build();
			} else {
				book.removeLinks();
				
				if (!book.isAvailable()) {
					result = ResponseEntity.status(HttpStatus.CONFLICT).body(book);

				} else {
					book.setAvailable(false);
					book.add(WebMvcLinkBuilder
							.linkTo(WebMvcLinkBuilder.methodOn(BookV3Controller.class).searchLending(book.getId()))
							.withRel("check-lending"));
					result = ResponseEntity.created(URI.create("/books-v2/lendings/" + book.getId())).body(book);
				}
			}
		}

		return result;
	}

	@GetMapping("/lendings/{idBook}")
	public ResponseEntity<BookV3> searchLending(@PathVariable("idBook") Integer id) {
		ResponseEntity<BookV3> result = null;

		if (id == null) {
			result = ResponseEntity.badRequest().build();
		} else {
			BookV3 book = bookList.stream().filter(b -> !b.isAvailable() && b.getId() == id).findFirst().orElse(null);

			if (book == null) {
				result = ResponseEntity.notFound().build();
			} else {
				book.removeLinks();
				book.add(WebMvcLinkBuilder
						.linkTo(WebMvcLinkBuilder.methodOn(BookV3Controller.class).searchLending(book.getId()))
						.withRel("self"));
				result = ResponseEntity.ok(book);
			}
		}

		return result;
	}
}
