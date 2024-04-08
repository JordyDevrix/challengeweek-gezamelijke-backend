import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {TokenService} from "../auth/logic/token.service";
import {CartService} from "../services/cart.service";

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss'
})
export class NavigationComponent {

  public  cartSize: number =  0;

  constructor(private tokenService: TokenService, private cartService: CartService) {
    this.tokenService = tokenService;
    this.cartService = cartService;
    this.cartSize = cartService.getCartSize();
  }

    ngOnInit() {
      this.cartService.$productCount.subscribe(count => this.cartSize = count);
    }

    isLoggedIn() {
      return this.tokenService.hasToken();
    }

    logout() {
      this.tokenService.removeToken();
    }

}
