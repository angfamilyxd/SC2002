package model;

import java.io.Serializable;

/**
 * Represents a review made by a moviegoer.
 */
public class Review implements Serializable{
	/**
	 * Name of the reviewer making this review.
	 */
    private String reviewer;
    
    /**
     * Rating given by the reviewer making this review.
     */
	private double rating;
	
	/**
	 * Content of the review by the reviewer making this review.
	 */
	private String reviewContent;

	/**
	 * Creates a new review with the given reviewer name, rating and content.
	 * @param name The name of the reviewer.
	 * @param rate The rating given by the reviewer.
	 * @param content The content of this review.
	 */
	public Review(String name, double rate, String content) {
		reviewer = name;
		rating = rate;
		reviewContent = content;
	}
	
	/**
	 * Gets the name of the reviewer who made this review.
	 * @return This review's reviewer name.
	 */
	public String getReviewer() {
		return reviewer;
	}
	
	/**
	 * Changes the name of the reviewer who made this review.
	 * @param r The new name of the reviewer.
	 */
	public void setReviewer(String r) {
		reviewer = r;
	}
	
	/**
	 * Gets the rating given by the reviewer of this review.
	 * @return
	 */
	public double getRating() {
		return rating;
	}
	
	/**
	 * Changes the rating for this review.
	 * @param r New rating for this review.
	 */
	public void setRating(double r) {
		rating = r;
	}
	
	/**
	 * Gets the content of this review.
	 * @return This review's content.
	 */
	public String getContent() {
		return reviewContent;
	}
	
	/**
	 * Changes the content of this review.
	 * @param c New content of this review.
	 */
	public void setContent(String c) {
		reviewContent = c;
	}
	
	/**
	 * Changes this review's attributes to string format for storage in database.
	 */
	@Override
	public String toString() {
		String returnString = "";
		returnString += "" + "Reviewer: " + this.reviewer + "\n";
		returnString += "   " + "Rating: " + String.format("%.1f",this.rating) + "\n";
		returnString += "   " + "Content: " + this.reviewContent;
		return returnString;
	}
	
}