package com.bookshelf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bookshelf {
	private List<Book> books;
	private double mean_goodreads_votes, mean_amazon_votes, mean_total;
	private Calculator calculator;
	
	public Bookshelf(List<Book> bookshelf) {
		this.books = bookshelf;
		this.calculator = new Calculator();
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
	
	public double getMeanGoodreadsVotes() {
		return mean_goodreads_votes;
	}

	public void setMeanGoodreadsVotes(double meanVotes) {
		this.mean_goodreads_votes = meanVotes;
	}
	
	public double getGoodreadsMinVotes() {
		return calculator.trimmean(getGoodreadsRatingsCountList());
	}

	public double getAmazonMinVotes() {
		return calculator.trimmean(getAmazonRatingsCountList());

	}
	public double getMeanAmazonVotes() {
		return mean_amazon_votes;
	}

	public void setMeanAmazonVotes(double meanVotes) {
		this.mean_amazon_votes = meanVotes;
	}
	
	public double getTotalMean() {
		return mean_total;
	}
	
	public void setTotalMean(double mean) {
		this.mean_total = mean;
	}

	public List<Integer> getGoodreadsRatingsCountList() {
		List<Integer> votes = new ArrayList<>();
		for (Book book: books) {
			votes.add(book.getGoodreadsRatingsCount());
		}

		Collections.sort(votes);
		return votes;
	}

	public List<Integer> getAmazonRatingsCountList() {
		List<Integer> votes = new ArrayList<>();
		for (Book book: books) {
			votes.add(book.getAmazonRatingsCount());
		}

		Collections.sort(votes);
		return votes;
	}
	
	public List<Integer> getTotalRatingsCountList() {
		List<Integer> votes = new ArrayList<>();
		votes.addAll(getGoodreadsRatingsCountList());
		votes.addAll(getAmazonRatingsCountList()); 

		Collections.sort(votes);
		return votes;
	}
}
