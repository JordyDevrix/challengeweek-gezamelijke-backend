import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../models/product.model';
import { ProductsService } from '../../services/products.service';
import { CartService} from "../../services/cart.service";
import { ProductDetailComponent} from "../product-detail/product-detail.component";

@Component({
  selector: 'app-product-page',
  standalone: true,
  imports: [
    ProductDetailComponent
  ],
  templateUrl: './product-page.component.html',
  styleUrl: './product-page.component.scss'
})
export class ProductPageComponent {
  private productId: string;
  private products: Product[] = [];
  public product: Product;
  public randomProducts: Product[] = [];
  @Output() public productAddedToCart: EventEmitter<Product> = new EventEmitter();

  constructor(private route: ActivatedRoute, private productService: ProductsService, private cartService: CartService) {
    this.route.params.subscribe( params => {
      this.productId = params["id"];

      this.loadFunction();
    });
  }
  ngOnInit(): void {
    // this.productService.getProductById(this.productId).subscribe((product: Product) => {
    //   this.product = product;
    // });
    // this.productService.getProducts().subscribe((products: Product[]) => {
    //   this.products = products;
    //
    //  // generate 3 random products, they should not be the same as the current product and not the same as each other
    //   for (let i = 0; i < 3; i++) {
    //     let randomProduct: Product;
    //     do {
    //       randomProduct = products[Math.floor(Math.random() * products.length)];
    //     } while (randomProduct.id === this.product.id || this.randomProducts.includes(randomProduct));
    //     this.randomProducts.push(randomProduct);
    //   }
    // });

    //
  }

  public loadFunction() {
    this.productService.getProductById(this.productId).subscribe((product: Product) => {
      this.product = product;
    });
    this.productService.getProducts().subscribe((products: Product[]) => {
      this.products = products;

     // generate 3 random products, they should not be the same as the current product and not the same as each other
      this.randomProducts = [];
      for (let i = 0; i < 3; i++) {
        let randomProduct: Product;
        do {
          randomProduct = products[Math.floor(Math.random() * products.length)];
        } while (randomProduct.id === this.product.id || this.randomProducts.includes(randomProduct));
        this.randomProducts.push(randomProduct);
      }
    });
  }



  public addToCart(product: Product) {
    this.cartService.addProductToCart(product);
  }
}
