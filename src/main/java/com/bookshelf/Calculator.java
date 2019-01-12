package com.bookshelf;

import java.util.Collections;
import java.util.List;

public class Calculator {
	private Bookshelf bookshelf;
	private final double percentage = .25;
	
	public Calculator(Bookshelf bookshelf) {
		this.bookshelf = bookshelf;
		calculateMeans();
		calculateAdjustedRatings();
	}

	public Calculator() {
	}

	private void calculateAdjustedRatings() {
		// TODO Auto-generated method stub
    	for (var i = 0; i < bookshelf.getNumberOfBooks(); i++) {
    		var book = bookshelf.getBook(i);
    		var adjustedRating = ((getAjustedRating(
					book.getGoodreadsRatingsCount(), 
					book.getGoodreadsAverageRating(), 
					bookshelf.getMeanGoodreadsVotes(),
					bookshelf.getGoodreadsMinVotes()) + getAjustedRating(
        					book.getAmazonRatingsCount(), 
        					book.getAmazonAverageRating(), 
        					bookshelf.getMeanAmazonVotes(),
        					bookshelf.getAmazonMinVotes())) / 2);
        	book.setAdjustedRating(adjustedRating);
    	}
	}

	private double getAjustedRating(int ratingsCount, double averageRating, double meanVote, double minVotes) {
		//double rating = averageRating * 2;
		return (ratingsCount / (ratingsCount + minVotes)) * averageRating + (minVotes / (ratingsCount + minVotes)) * meanVote;
	}

	private void calculateMeans() {
		double goodreadsVotes = 0, amazonVotes = 0, total = 0;
		for (var i = 0; i < bookshelf.getNumberOfBooks(); i++) {
			// for (Book book: bookshelf) { // TODO Iterator
			goodreadsVotes += bookshelf.getBook(i).getGoodreadsAverageRating();
			amazonVotes += bookshelf.getBook(i).getAmazonAverageRating();
			total += bookshelf.getBook(i).getGoodreadsAverageRating() + bookshelf.getBook(i).getAmazonAverageRating();
		}

		bookshelf.setMeanGoodreadsVotes(goodreadsVotes / bookshelf.getNumberOfBooks());
		bookshelf.setMeanAmazonVotes(amazonVotes / bookshelf.getNumberOfBooks());
		bookshelf.setTotalMean((total/2)/bookshelf.getNumberOfBooks());
	}
	
	public double trimmean(List<Integer> votes) {
		Collections.sort(votes);
		
		double removeAmount = (votes.size() * percentage) / 2;
		int roundDownNearedMultipleTwo = (int) (removeAmount >= 0 ? (removeAmount / 2) * 2 : ((removeAmount - 2 + 1) / 2) * 2);
		return getMean(votes.subList(roundDownNearedMultipleTwo, votes.size() - roundDownNearedMultipleTwo));
	}
	
	private double getMean(List<Integer> array) {
		double sum = 0;
	    for (int i = 0; i < array.size(); i++) {
	        sum += array.get(i);
	    }
	    return sum / array.size();
	}
}
