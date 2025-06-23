package com.airfrance.avisdevol.controller;

import com.airfrance.avisdevol.model.Comment;
import com.airfrance.avisdevol.model.Review;
import com.airfrance.avisdevol.repository.CommentRepository;
import com.airfrance.avisdevol.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews/{reviewId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByReviewId(@PathVariable Long reviewId) {
        // First check if review exists
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        // Get comments for the review
        List<Comment> comments = commentRepository.findByReviewId(reviewId);
        // Return empty list instead of 404 if no comments found
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@PathVariable Long reviewId, @RequestBody Comment comment) {
        // First check if review exists
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            // Set the review and save
            comment.setReview(review);
            Comment savedComment = commentRepository.save(comment);
            return ResponseEntity.ok(savedComment);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
