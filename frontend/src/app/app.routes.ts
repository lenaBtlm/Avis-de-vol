import { Routes } from '@angular/router';
import { ReviewList } from './components/review-list/review-list';
import { ReviewForm } from './components/review-form/review-form';

export const routes: Routes = [
  { path: '', component: ReviewForm },
  { path: 'reviews', component: ReviewList }
];
