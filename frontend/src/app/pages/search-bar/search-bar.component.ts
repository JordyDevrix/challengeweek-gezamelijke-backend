import { Component } from '@angular/core';
import stringSimilarity from 'string-similarity';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.scss'
})
export class SearchBarComponent {


  productList = [
    "AKAI MPK mini mk3", "AKAI MPK mini mk2", "AKAI MPK mini MK1", "AKAI Mini plus", "AKAI mk3 plus", "AKAI MK2 plus", "AKAI LPK25", "Devine Ez creator", "M-Audio Keystation MK3", "Devine Ez creator plus", "M-Audio Keystation mini MK3", "AKAI Professional APC key", "Arturia Keylab MK3"
  ];

// Replace this with all the catogory's that exist
  categoryList = [
    "keyboards", "speakers", "cables", "microphones", "headphones", "controllers", "mixers", "interfaces", "stands", "instruments", "software"
  ];

  myString = "keyboard"
  stringSimilarity = stringSimilarity
  public search() {
    let productsFound = [];
    console.log("Results by direct match:");
    for (let i = 0; i < this.productList.length; i++) {
      // Vergelijk de string (uit de zoekbalk) met elk product in de lijst van GET /api/pub/product/all
      let ratio = 0;
      ratio = this.stringSimilarity.compareTwoStrings(this.productList[i].toLowerCase(), this.myString.toLowerCase());
      if (ratio > 0.2) {
        // Als de ratio hoger is dan 0.2, print dan het product
        let product= "";
        product = this.productList[i];
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
          if (productsFound.includes(product)) {
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

