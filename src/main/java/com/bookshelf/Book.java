package com.bookshelf;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	private String isbn;
	private String title, image, href, price, description;
	private double amazonAverageRating, goodreadsAverageRating, adjustedRating;
	private int amazonRatingsCount, goodreadsRatingsCount;
	
//	public Book(String isbn) {
//		this.isbn = isbn;
//	}
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public double getAmazonAverageRating() {
		return amazonAverageRating;
	}
	public void setAmazonAverageRating(double amazonAverageRating) {
		this.amazonAverageRating = amazonAverageRating;
	}
	public int getAmazonRatingsCount() {
		return amazonRatingsCount;
	}
	public void setAmazonRatingsCount(int amazonRatingsCount) {
		this.amazonRatingsCount = amazonRatingsCount;
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
	public void setAdjustedRating(double adjustedRating) {
		this.adjustedRating = adjustedRating;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
