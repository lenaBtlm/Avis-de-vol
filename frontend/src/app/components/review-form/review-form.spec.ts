import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { ReviewForm } from './review-form';
import { HttpClient } from '@angular/common/http';
import { provideHttpClient, HttpResponse } from '@angular/common/http';
import { provideHttpClientTesting, TestRequest, HttpTestingController } from '@angular/common/http/testing';
import { provideRouter } from '@angular/router';
import { FormsModule } from '@angular/forms';

describe('ReviewForm', () => {
  let component: ReviewForm;
  let fixture: ComponentFixture<ReviewForm>;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReviewForm,
        FormsModule
      ],
      providers: [
        provideHttpClient(),
        provideHttpClientTesting(),
        provideRouter([])
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ReviewForm);
    component = fixture.componentInstance;
    httpMock = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with empty form data', () => {
    expect(component.formData).toEqual({
      flightNumber: '',
      date: '',
      company: '',
      comment: ''
    });
    expect(component.rating).toBe(0);
  });

  it('should update rating when setRating is called', () => {
    component.setRating(4);
    expect(component.rating).toBe(4);
  });

  it('should send review data to the server', fakeAsync(() => {
    const testData = {
      flightNumber: 'AF123',
      date: '2023-06-22',
      company: 'Air France',
      comment: 'Great flight!'
    };
    component.formData = testData;
    component.rating = 5;

    component.send();

    const req = httpMock.expectOne('/api/reviews');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual({
      flightNumber: testData.flightNumber,
      dateOfFlight: testData.date,
      company: testData.company,
      comment: testData.comment,
      rating: 5
    });

    req.flush({ success: true });
    tick();

    expect(component.formData).toEqual({
      flightNumber: '',
      date: '',
      company: '',
      comment: ''
    });
    expect(component.rating).toBe(0);
  }));

  it('should handle server errors', fakeAsync(() => {
    spyOn(console, 'error');

    component.send();

    const req = httpMock.expectOne('/api/reviews');
    req.error(new ErrorEvent('Network error'));
    tick();
    
    expect(console.error).toHaveBeenCalled();
  }));
});