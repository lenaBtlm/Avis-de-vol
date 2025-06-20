import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { provideHttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  providers: [],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'frontend';
  
  constructor(private http: HttpClient) {}

  formData = {
    flightNumber: '',
    date: '',
    company: '',
    comment: ''
  };
  
  rating = 0;

  setRating(value: number) {
    this.rating = value;
  }

  send() {
    const payload = {
      flightNumber: this.formData.flightNumber,
      comment: this.formData.comment,
      rating: this.rating,
      dateOfFlight: this.formData.date
    };

    this.http.post('/api/reviews', payload)
      .subscribe({
        next: (response) => {
          console.log('Review submitted successfully:', response);
          // Reset form
          this.formData = {
            flightNumber: '',
            date: '',
            company: '',
            comment: ''
          };
          this.rating = 0;
        },
        error: (error) => {
          console.error('Error submitting review:', error);
        }
      });
  }
}
