import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Product } from '../../models/product.model';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.scss'
})
export class ProductDetailComponent {
  @Input() public product_detail: Product;

  @Output() public productAddedToCart: EventEmitter<Product> = new EventEmitter();

  public addToCart(product: Product) {
    this.productAddedToCart.emit(product);
  }
}
