package com.airfrance.avisdevol.repository;

import com.airfrance.avisdevol.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFlightNumber(String flightNumber);
    List<Review> getReviewsByRating(Integer rating);
    List<Review> getReviewsByDateOfFlight(String dateOfFlight);
    List<Review> findByCommentsIsEmpty();
}
