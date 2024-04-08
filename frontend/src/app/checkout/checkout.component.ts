import { Component } from '@angular/core';
import { CartService } from '../services/cart.service';
import {Product} from "../models/product.model";
import {Router, RouterLink} from "@angular/router";
import {OrderService} from "../services/order.service";
import {CreateOrder} from "../models/createOrder.model";



@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.scss'
})
export class CheckoutComponent {
  cartItems: Product[] = []
  cartTotalPrice: number = 0;
  cartSize: number = 0;
  checkoutButtonDisabled: boolean = false;

  constructor(public cartService: CartService, private orderService: OrderService, private router: Router) {
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

  protected readonly parseFloat = parseFloat;
  protected readonly String = String;

  public removeProduct(product: Product): void {
    this.cartService.removeProductFromCart(product);
    this.cartItems = this.cartService.getProductsInCart();
    this.cartTotalPrice = this.cartService.calculateTotalPrice();
    this.cartSize = this.cartService.getCartSize();
  }

  public checkout(status: string) {
    // disable the checkout button
  this.checkoutButtonDisabled = true;

    const orderProducts: any[] = []
    for (let product of this.cartItems) {
      for (let i = 0; i < product.quantity; i++) {
        orderProducts.push(<Product>{
          id: product.id,
          name: product.name,
          description: product.description,
          stock: product.stock,
          price: product.price,
          imageUrl: product.imageUrl,
          brand: product.brand,
          instruction: product.instruction,
          terms: product.terms,
        });
      }
    }
    const order = new CreateOrder(orderProducts, status);
    this.orderService.createOrder(order).subscribe((order: CreateOrder) => {
      this.cartService.clearCart();
      this.cartItems = this.cartService.getProductsInCart();
      this.cartTotalPrice = this.cartService.calculateTotalPrice();
      this.cartSize = this.cartService.getCartSize();
      // navigate to home page with success parameter in the URL
      this.router.navigate(['/'], { queryParams: { success: true } });
    } );
  }

}
