package com.bookshelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bookshelf {
	private List<Book> books;
	
	public Bookshelf(List<Book> bookshelf) {
		this.books = bookshelf;
	}
	
	public int getNumberOfBooks() {
		return books.size();
	}
	
	public Book getBook(int index) {
		return books.get(index);
	}
	
	public List<Book> getBooks() {
		return books;
	}
}
