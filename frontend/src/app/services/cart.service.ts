import { Injectable } from '@angular/core';
import {Product} from "../models/product.model";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private productsInCart: Product[] = [];
  public $productCount: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor() { }

  public getProductsInCart(): Product[] {
    return this.productsInCart.slice();
  }

  public addProductToCart(product: Product): void {
    // Check if product is already in cart
    const productInCart = this.productsInCart.find(p => p.id === product.id);
    if (productInCart) {
      productInCart.quantity++;
    } else {
      product.quantity = 1;
      this.productsInCart.push(product);
    }
    this.$productCount.next(this.getCartSize());
  }

  public calculateTotalPrice(): number {
    let totalPrice = 0;
    this.productsInCart.forEach(p => totalPrice += p.price * p.quantity);
    return totalPrice;
  }

  public removeProductFromCart(product: Product): void {
    this.productsInCart = this.productsInCart.filter(p => p !== product);
    this.$productCount.next(this.getCartSize());
  }

  public clearCart(): void {
    this.productsInCart = [];
    this.$productCount.next(this.getCartSize());
  }

  public getCartSize(): number {
    // Calculate total amount of products in cart with quantity
    let totalAmount = 0;
    this.productsInCart.forEach(p => totalAmount += p.quantity);
    this.$productCount.next(totalAmount);
    return totalAmount;
  }
}
