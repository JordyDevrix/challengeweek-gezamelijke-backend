import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, Input } from '@angular/core';
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

}
