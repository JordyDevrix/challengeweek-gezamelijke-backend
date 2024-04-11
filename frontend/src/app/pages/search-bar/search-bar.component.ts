import { Component } from '@angular/core';
import stringSimilarity from 'string-similarity';
import {FaIconComponent, FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { faMagnifyingGlass } from '@fortawesome/free-solid-svg-icons';
import {ProductsService} from "../../services/products.service";
import {Product} from "../../models/product.model";

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [
    FaIconComponent
  ],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.scss'
})
export class SearchBarComponent {
  productList: Product[] = [];
  myString = ""
  faMagnifyingGlass = faMagnifyingGlass;

  constructor(private productService: ProductsService) { }

  ngOnInit()   {
    this.productService
      .getProducts()
      .subscribe((productList: Product[]) => {
        this.productList = productList;
      });
  }

// Replace this with all the catogory's that exist
  categoryList = [
    "keyboards", "speakers", "cables", "microphones", "headphones", "controllers", "mixers", "interfaces", "stands", "instruments", "software"
  ];

  newEvent(event: any) {
    // console.log(event.target.value)
    this.myString = event.target.value;
  }

  stringSimilarity = stringSimilarity
  public search() {
    // console.log("Query: " + this.myString)
    let productsFound = [];
    console.log("Results by direct match:");
    for (let i = 0; i < this.productList.length; i++) {
      // Vergelijk de string (uit de zoekbalk) met elk product in de lijst van GET /api/pub/product/all
      // console.log(`${this.productList[i].brand} ${this.productList[i].name}`)
      let ratio = 0;

      try {
        ratio = this.stringSimilarity.compareTwoStrings(
          `${this.productList[i].brand.toLowerCase()} ${this.productList[i].name.toLowerCase()}`,
          this.myString.toLowerCase());

      } catch (e) {
        if (this.productList[i].brand == null) {
          // console.log(`${this.productList[i].name.toLowerCase()}`)
          ratio = this.stringSimilarity.compareTwoStrings(`${this.productList[i].name.toLowerCase()}`,
            this.myString.toLowerCase());

        } else if (this.productList[i].name == null) {
          // console.log(`${this.productList[i].brand.toLowerCase()}`)
          ratio = this.stringSimilarity.compareTwoStrings(`${this.productList[i].brand.toLowerCase()}`,
            this.myString.toLowerCase());

        } else {
          console.log(e)
        }
      }

      if (ratio > 0.2) {
        // Als de ratio hoger is dan 0.2, print dan het product
        let product= "";
        product = `${this.productList[i].brand} ${this.productList[i].name}`;
        // Voeg elk product toe aan de lijst van producten met een minimale ratio van 0.2
        productsFound.push(product);
        console.log(this.myString + "\t" + ratio + "\t" + product);
      }
    }

    for (let i = 0; i < this.categoryList.length; i++) {
      // Vergelijk de string (uit de zoekbalk) met elke category in de lijst van GET /api/pub/category/all
      let ratio = 0;
      ratio = this.stringSimilarity.compareTwoStrings(this.categoryList[i].toLowerCase(), this.myString.toLowerCase());
      if (ratio > 0.55) {
        console.log(`Results by category '${this.categoryList[i]}':`);
        // Hier ga je een request sturen voor alle categorieën die een ratio van 0.55 of hoger hebben
        // (E.G: GET /api/product/category/keyboards)
        for (const product of this.productList) {
          if (productsFound.includes(`${product.brand} ${product.name}`)) {
            continue;
          } else {
            // Hier kan je elk product uit de betreffende categorie printen die je van te voren hebt aangeroep op een get category endpoint
            console.log(this.myString + "\t" + ratio + "\t" + product);
          }
        }
      }
    }
  }
}

