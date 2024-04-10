import { Component, OnInit } from '@angular/core';
import {RouterLink} from "@angular/router";
import {TokenService} from "../auth/logic/token.service";
import {CartService} from "../services/cart.service";
import {Router, NavigationEnd} from '@angular/router'
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    RouterLink, NgIf
  ],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.scss'
})
export class NavigationComponent implements OnInit{

  public  cartSize: number =  0;

  constructor(private tokenService: TokenService, private cartService: CartService, private router: Router) {
    this.tokenService = tokenService;
    this.cartService = cartService;
    this.cartSize = cartService.getCartSize();
  }

    showCategoryBar: boolean = true;

    ngOnInit(): void {
      this.cartService.$productCount.subscribe(count => this.cartSize = count);
      this.router.events.subscribe(event => {
        if (event instanceof NavigationEnd){
          this.showCategoryBar = (this.router.url == '/' || this.router.url == '/#')
        }
    })
  }

    isLoggedIn() {
      return this.tokenService.hasToken();
    }

    logout() {
      this.tokenService.removeToken();
    }

    public onMouseEnter(event: MouseEvent) {
      (event.target as HTMLElement).style.color = 'red';
      (event.target as HTMLElement).style.boxShadow = '0 0 5px rgba(0, 0, 0, 0.5)'; 
    }

    public onMouseLeave(event: MouseEvent) {
      (event.target as HTMLElement).style.color = '';
      (event.target as HTMLElement).style.boxShadow = ''; 
    }

    

}
