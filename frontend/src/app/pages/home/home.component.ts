import { Component } from '@angular/core';
import {ProductIndexComponent} from "../../product/product-index/product-index.component";
import {ProductHeaderComponent} from "../../product/product-header/product-header.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ProductIndexComponent,
    ProductHeaderComponent,
    RouterLink
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

}
