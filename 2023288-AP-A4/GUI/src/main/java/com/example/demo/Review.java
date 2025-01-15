package com.example.demo;

public class Review {
    private int rating;         // 1 to 5
    private String reviewText;  // Optional text review
    private String reviewerName;

    public Review(int rating, String reviewText, String reviewerName) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewerName = reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    @Override
    public String toString() {
        return "Rating: " + rating + " | Review: " + reviewText + " | By: " + reviewerName;
    }
}

