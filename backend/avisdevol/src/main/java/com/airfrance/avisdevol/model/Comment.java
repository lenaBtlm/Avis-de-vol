package com.airfrance.avisdevol.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;
    
    private boolean isPublic = false;
}
