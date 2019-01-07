package com.bookshelf;

import java.util.List;

public interface GoodreadsService {
	//public abstract List<Book> getBooksByISBN(String[] isbns);
	public abstract List<Book> addGoodreadsBooks(List<Book> books);
}
