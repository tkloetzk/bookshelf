package com.bookshelf;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	private String isbn;
	private String description;
	private double goodreadsAverageRating;
	private int goodreadsRatingsCount;

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
