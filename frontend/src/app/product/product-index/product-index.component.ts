import { Component } from '@angular/core';
import { Product } from '../../models/product.model';
import { ProductDetailComponent } from '../product-detail/product-detail.component';
import {ProductsService} from "../../services/products.service";
import {CartService} from "../../services/cart.service";
import {ToastService} from "../../services/toast.service";

@Component({
  selector: 'app-product-index',
  standalone: true,
  imports: [ProductDetailComponent],
  templateUrl: './product-index.component.html',
  styleUrl: './product-index.component.scss'
})
export class ProductIndexComponent {
  public products: Product[] = [];
  public sliderProducts: Product[] = [];

  constructor(private productService: ProductsService, private cartService: CartService, private toastService: ToastService) {
  }

  ngOnInit()   {
     this.productService
       .getProducts()
       .subscribe((products: Product[]) => {
         this.products = products;
         // scramble the products
          this.products.sort(() => Math.random() - 0.5);
    });
  }

  public onProductAddedToCart(product: Product) {
    this.cartService.addProductToCart(product);
  }

}
