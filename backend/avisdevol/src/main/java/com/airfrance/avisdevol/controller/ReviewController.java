package com.airfrance.avisdevol.controller;

import com.airfrance.avisdevol.model.Review;
import com.airfrance.avisdevol.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review);
    }

    @GetMapping("/flight/{flightNumber}")
    public List<Review> getReviewsByFlightNumber(@PathVariable String flightNumber) {
        return reviewRepository.findByFlightNumber(flightNumber);
    }

    @GetMapping("/score/{rating}")
    public List<Review> getReviewsByRating(@PathVariable Integer rating) {
        return reviewRepository.getReviewsByRating(rating);
    }

    @GetMapping("/date/{dateOfFlight}")
    public List<Review> getReviewsByDateOfFlight(@PathVariable String dateOfFlight) {
        return reviewRepository.getReviewsByDateOfFlight(dateOfFlight);
    }
}
