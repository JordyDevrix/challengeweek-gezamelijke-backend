import { Component } from '@angular/core';
import {Product} from "../models/product.model";
import {CartService} from "../services/cart.service";
import {ProductDetailComponent} from "../product/product-detail/product-detail.component";
import {RouterLink} from "@angular/router";
import {Router} from "@angular/router";
import {AuthService} from "../auth/logic/auth.service";




@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    ProductDetailComponent,
    RouterLink
  ],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {
  public cartItems: Product[] = [];
  public cartTotalPrice: number = 0;
  public cartSize: number = 0;

  constructor(private cartService: CartService, private router: Router, private authService: AuthService) {
    this.cartItems = this.cartService.getProductsInCart();
    this.cartTotalPrice = this.cartService.calculateTotalPrice();
    this.cartSize = this.cartService.getCartSize();
  }

  public lowerQuantity(product: Product): void {
    if (product.quantity > 1) {
      product.quantity--;
    } else {
      this.cartService.removeProductFromCart(product);
    }
    this.cartItems = this.cartService.getProductsInCart();
    this.cartTotalPrice = this.cartService.calculateTotalPrice();
    this.cartSize = this.cartService.getCartSize();
  }

  public addQuantity(product: Product): void {
    product.quantity++;
    this.cartTotalPrice = this.cartService.calculateTotalPrice();
    this.cartSize = this.cartService.getCartSize();
  }

  public clearCart(): void {
    console.log('Clearing cart');
    this.cartService.clearCart();
    this.cartItems = this.cartService.getProductsInCart();
    this.cartTotalPrice = this.cartService.calculateTotalPrice();
    this.cartSize = this.cartService.getCartSize();
  }

  navigateToCheckout() {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['/checkout']);
    } else {
      this.router.navigate(['/login'], { queryParams: { returnUrl: '/checkout' }});
    }
  }

}
