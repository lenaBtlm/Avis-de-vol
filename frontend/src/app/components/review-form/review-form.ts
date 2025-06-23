import { Component, ViewChild } from '@angular/core';
import { FormsModule, NgForm  } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-review-form',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './review-form.html',
  styleUrl: './review-form.css'
})
export class ReviewForm {
  @ViewChild('myForm') myForm!: NgForm;
  
  formData = {
    flightNumber: '',
    date: '',
    company: '',
    comment: ''
  };
  
  rating = 0;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  setRating(value: number) {
    this.rating = value;
  }

  send() {
    const payload = {
      flightNumber: this.formData.flightNumber,
      comment: this.formData.comment,
      rating: this.rating,
      dateOfFlight: this.formData.date,
      company: this.formData.company
    };

    this.http.post('/api/reviews', payload)
      .subscribe({
        next: (response) => {
          console.log('Review submitted successfully:', response);
          this.formData = { 
            flightNumber: '',
            date: '',
            company: '',
            comment: ''
          };
          this.rating = 0;
          this.myForm.resetForm();
        },
        error: (error) => {
          console.error('Error submitting review:', error);
        }
      });
  }

  viewReviews() {
    this.router.navigate(['/reviews']);
  }
}
