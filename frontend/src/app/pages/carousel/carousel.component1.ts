import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { HomeComponent } from '../home/home.component';
import {ProductHeaderComponent} from "../../product/product-header/product-header.component";

@Component({
  selector: 'app-carousel',
  standalone: true,
  templateUrl: './carousel.component1.html',
  styleUrls: ['./carousel.component1.scss'],
  imports: [
    CommonModule, HomeComponent, ProductHeaderComponent
  ],
})

export class CarouselComponent1 {


}
