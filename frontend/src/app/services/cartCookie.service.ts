import { Injectable } from '@angular/core';
import {Product} from "../models/product.model";
import {BehaviorSubject} from "rxjs";
import {CookieService} from "./cookie.service";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  public $productCount: BehaviorSubject<number> = new BehaviorSubject<number>(0);

  constructor(private cookieService: CookieService) {
    this.$productCount.next(this.getCartSize());
  }
    // if not exists, create cart cookie


  public getProductsInCart(): Product[] {
    // get products from cart cookie
    let cookie = this.cookieService.getCookie('cart')
    if (cookie === null) {
      return [];
    }
    return JSON.parse(cookie);
  }

  public addProductToCart(product: Product): void {
    // Check if product is already in cart and add to cart cookie
    let productsInCart = this.getProductsInCart();
    const productInCart = productsInCart.find(p => p.id === product.id);
    if (productInCart) {
      productInCart.quantity++;
    } else {
      product.quantity = 1;
      productsInCart.push(product);
    }
    this.cookieService.setCookie('cart', JSON.stringify(productsInCart), 1);
    this.$productCount.next(this.getCartSize());

  }

  public calculateTotalPrice(): number {
    let totalPrice = 0;
    this.getProductsInCart().forEach(p => totalPrice += p.price * p.quantity);
    return totalPrice;
  }

  public removeProductFromCart(product: Product): void {
    let productsInCart = this.getProductsInCart();
    productsInCart = productsInCart.filter(p => p !== product);
    this.cookieService.setCookie('cart', JSON.stringify(productsInCart), 1);
    this.$productCount.next(this.getCartSize());
  }

  public clearCart(): void {
    this.cookieService.eraseCookie('cart');
    this.$productCount.next(this.getCartSize());
  }

  public getCartSize(): number {
    // Calculate total amount of products in cart with quantity
    let totalAmount = 0;
    let productsInCart = this.getProductsInCart();
    productsInCart.forEach(p => totalAmount += p.quantity);
    this.$productCount.next(totalAmount);
    return totalAmount;
  }
}
