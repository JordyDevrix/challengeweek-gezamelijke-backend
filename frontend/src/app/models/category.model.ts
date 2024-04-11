import {Product} from "./product.model";

export class Category {

  public id: string
  public name: string
  public products: Product[]

  constructor(id: string, name: string, products: []) {
    this.id = id;
    this.name = name;
    this.products = products
  }
}
