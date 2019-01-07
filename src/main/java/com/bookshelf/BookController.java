package com.bookshelf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookshelf.GoodreadsService;

@RestController
@RequestMapping("/api/goodreads/v1")
public class BookController {
	
	@Autowired
	GoodreadsService goodreadsService;

//	@RequestMapping(value="/{isbns}", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<Object> getBook(@PathVariable("isbns") final String isbns) {
//		String[] isbnArray = isbns.split(",");
//		return new ResponseEntity<>(goodreadsService.getBooksByISBN(isbnArray), HttpStatus.OK);
//	}
	
	@RequestMapping(value="/bookshelf", method = RequestMethod.POST)
	public ResponseEntity<Object> getBook(@RequestBody List<Book> bookshelf) {
		return new ResponseEntity<>(goodreadsService.addGoodreadsBooks(bookshelf), HttpStatus.OK);
	}
	
	
}
