import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-carousel',
  standalone: true,
  templateUrl: './carousel.component1.html',
  styleUrls: ['./carousel.component1.scss'],
  imports: [
    CommonModule, HomeComponent
  ],
})

export class CarouselComponent1 {
  @Input() slides: any[];

  currentSlide = 0;

  constructor() {}

  onPreviousClick() {
    const previous = this.currentSlide - 1;
    this.currentSlide = previous < 0 ? this.slides.length - 1 : previous;
    console.log("previous clicked, new current slide is: ", this.currentSlide);
  }
  
  onNextClick() {
    const next = this.currentSlide + 1;
    this.currentSlide = next === this.slides.length ? 0 : next;
    console.log("next clicked, new current slide is: ", this.currentSlide);
  }


}
