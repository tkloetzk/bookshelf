package com.bookshelf;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class GoodreadsBook {
	@Id
	private String isbn;
	private String title;
	private double goodreadsAverageRating;
	private int goodreadsRatingsCount;
	
	public GoodreadsBook(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn.trim();
	}
	public String getISBN() {
		return isbn;
	}

	public void setGoodreadsAverageRating(double averageRating) {
		this.goodreadsAverageRating = averageRating;
	}
	public double getGoodreadsAverageRating() {
		return goodreadsAverageRating;
	}
	public void setGoodreadsRatingsCount(int ratingsCount) {
		this.goodreadsRatingsCount = ratingsCount;
	}
	public int getGoodreadsRatingsCount() {
		return goodreadsRatingsCount;
	}
}
