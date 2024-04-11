import { Component } from '@angular/core';
import {ProductIndexComponent} from "../../product/product-index/product-index.component";
import {ProductHeaderComponent} from "../../product/product-header/product-header.component";
import {RouterLink} from "@angular/router";
import {SearchBarComponent } from '../search-bar/search-bar.component';
import { CarouselComponent1 } from '../carousel/carousel.component1';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ProductIndexComponent,
    ProductHeaderComponent,
    RouterLink, 
    SearchBarComponent,
    CarouselComponent1
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  // get url parameters and if success=true, show success message
  public success: boolean = false;

  constructor() {
    const urlParams = new URLSearchParams(window.location.search);
    this.success = urlParams.get('success') === 'true';
  }

  public slides = [
    { src: "https://miro.medium.com/v2/resize:fit:1400/format:webp/1*mQb_Psavd1aGLlMXJJ2HvA.png" },
    { src: "https://d2rfa446ja7yzb.cloudfront.net/eyJidWNrZXQiOiJtaXJyb3IuZ2V0Zmxvd2JveC5jb20uZXUtd2VzdC0xLmxpdmUiLCJrZXkiOiJhSFIwY0hNNkx5OTNkM2N1YVc1emRHRm5jbUZ0TG1OdmJTOXdMME0wUjJWS1h6VnZYMko2THc9PS8wIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJ3ZWJwIiwicmVzaXplIjp7IndpZHRoIjo2NDB9fX0=" },
    { src: "https://d2rfa446ja7yzb.cloudfront.net/eyJidWNrZXQiOiJtaXJyb3IuZ2V0Zmxvd2JveC5jb20uZXUtd2VzdC0xLmxpdmUiLCJrZXkiOiJhSFIwY0hNNkx5OTNkM2N1YVc1emRHRm5jbUZ0TG1OdmJTOXdMME16VFRkRlQxZHZZMFYwTHc9PS8wIiwiZWRpdHMiOnsidG9Gb3JtYXQiOiJ3ZWJwIiwicmVzaXplIjp7IndpZHRoIjo2NDB9fX0=" },
    { src: "https://image4.com" }
];

}
