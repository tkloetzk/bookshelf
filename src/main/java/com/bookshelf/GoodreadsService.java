package com.bookshelf;

import java.util.List;

public interface GoodreadsService {
	public abstract GoodreadsBook getBookByISBN(String isbn);
	public abstract List<Book> addGoodreadsBooks(List<Book> books);
}
