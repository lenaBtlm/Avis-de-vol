package com.airfrance.avisdevol.controller;

import com.airfrance.avisdevol.model.Review;
import com.airfrance.avisdevol.model.StatusDTO;
import com.airfrance.avisdevol.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/company/{company}")
    public List<Review> getReviewsByCompany(@PathVariable String company) {
        return reviewRepository.getReviewsByCompany(company);
    }

    @GetMapping("/no-comment")
    public List<Review> getReviewsWithoutComment() {
        return reviewRepository.findByCommentsIsEmpty();
    }

    @PostMapping("/{id}/status")
    public ResponseEntity<Review> addStatusReview(@PathVariable Long id, @RequestBody StatusDTO status) {
        Optional<Review> reviewOpt = reviewRepository.findById(id);
        if (reviewOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Review review = reviewOpt.get();
        review.setStatus(status.getStatus());
        Review updatedReview = reviewRepository.save(review);
        return ResponseEntity.ok(updatedReview);
    }
}
