import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'frontend';
  
  formData = {
    numVol: '',
    date: '',
    compagnie: '',
    avis: ''
  };
  
  rating = 0;

  setRating(value: number) {
    this.rating = value;
  }

  send() {
    console.log('Form submitted:', { ...this.formData, rating: this.rating });
  }
}
