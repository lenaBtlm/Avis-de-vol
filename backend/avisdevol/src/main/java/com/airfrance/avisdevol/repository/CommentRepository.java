package com.airfrance.avisdevol.repository;

import com.airfrance.avisdevol.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM Comment c WHERE c.review.id = :reviewId")
    List<Comment> findByReviewId(@Param("reviewId") Long reviewId);
}
