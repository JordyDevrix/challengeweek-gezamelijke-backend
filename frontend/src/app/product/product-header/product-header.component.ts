import {Component} from '@angular/core';
import {Product} from "../../models/product.model";
import {ProductsService} from "../../services/products.service";
import {CartService} from "../../services/cart.service";
import {RouterLink} from "@angular/router";
import {ToastService} from "../../services/toast.service";


@Component({
  selector: 'app-product-header',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './product-header.component.html',
  styleUrl: './product-header.component.scss'
})
export class ProductHeaderComponent {
  public products: Product[] = [];
  public randomProduct: Product;
  public sliderProducts: Product[] = [];

  constructor(private productService: ProductsService, private cartService: CartService, private toastService: ToastService) {
  }

  ngOnInit()   {
    this.productService
      .getProducts()
      .subscribe((products: Product[]) => {
        this.products = products;

        // select random product for header
        let randomIndex = Math.floor(Math.random() * this.products.length);
        this.randomProduct = this.products[randomIndex];
      });
  }

public onProductAddedToCart(product: Product) {
    this.cartService.addProductToCart(product);
  }

}


