import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { RouterModule } from '@angular/router';

interface Review {
  id?: number;
  flightNumber: string;
  comment: string;
  rating: number;
  dateOfFlight: string;
  company: string;
}

@Component({
  selector: 'app-review-list',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './review-list.html',
  styleUrl: './review-list.css'
})
export class ReviewList implements OnInit {
  reviews: Review[] = [];
  error: string | null = null;

  constructor(
    private http: HttpClient,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.loadReviews();
  }

  loadReviews() { 
    this.http.get<Review[]>('/api/reviews').subscribe({
      next: (data) => {
        if (Array.isArray(data)) {
          this.reviews = [...data];
          console.log('Reviews array updated:', this.reviews);
          this.cdr.detectChanges();
        }
      },
      error: (error: HttpErrorResponse) => {
        console.error('Error loading reviews:', error);
        this.error = `Error loading reviews: ${error.message}`;
        this.cdr.detectChanges();
      }
    });
  }
}
