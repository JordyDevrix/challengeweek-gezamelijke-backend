export class Product {

  public id: string
  public name: string
  public description: string
  public stock: number
  public price: number
  public imageUrl: string
  public brand: string
  public instruction: string
  public terms: string
  public category: string
  public quantity: number = 1


  constructor(id: string, name: string, description: string, stock: number, price: number, imageUrl: string, brand: string, instruction: string, terms: string, category: string) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.stock = stock;
    this.price = price;
    this.imageUrl = imageUrl;
    this.brand = brand;
    this.instruction = instruction;
    this.terms = terms;
    this.category = category;
  }
}
