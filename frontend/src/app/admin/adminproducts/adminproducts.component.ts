import { Component, Directive, EventEmitter, Input, Output, QueryList, ViewChildren } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import {ProductsService} from "../../services/products.service";
import {Product} from "../../models/product.model";

interface ProductInterface {
  id: string;
  name: string;
  description: string;
  webshop: string;
  price: number;
  stock: number;
}

const Products: ProductInterface[] = [];

export type SortColumn = keyof ProductInterface | '';
export type SortDirection = 'asc' | 'desc' | '';
const rotate: { [key: string]: SortDirection } = { asc: 'desc', desc: '', '': 'asc' };

const compare = (v1: string | number, v2: string | number) => (v1 < v2 ? -1 : v1 > v2 ? 1 : 0);

export interface SortEvent {
  column: SortColumn;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  standalone: true,
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()',
  },
})
export class NgbdSortableHeader {
  @Input() sortable: SortColumn = '';
  @Input() direction: SortDirection = '';
  @Output() sort = new EventEmitter<SortEvent>();

  rotate() {
    this.direction = rotate[this.direction];
    this.sort.emit({ column: this.sortable, direction: this.direction });
  }
}

@Component({
  selector: 'app-adminproducts',
  standalone: true,
  imports: [DecimalPipe, NgbdSortableHeader],
  templateUrl: './adminproducts.component.html',
  styleUrl: './adminproducts.component.scss'
})
export class AdminProductsComponent {

  constructor(private productsService: ProductsService) { }

  ngOnInit() {
    this.productsService.getProducts().subscribe((products: Product[]) => {
      for (let product of products) {
        console.log(product);
        console.log(product.price);
        Products.push({
          id: product.id,
          name: product.name,
          description: product.description,
          webshop: product.id.split('-')[0].toLowerCase(),
          price: product.price,
          stock: product.stock
        });
      }
    });
  }

  products = Products;

  @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;

  onSort({ column, direction }: SortEvent) {
    // resetting other headers
    for (const header of this.headers) {
      if (header.sortable !== column) {
        header.direction = '';
      }
    }

    // sorting countries
    if (direction === '' || column === '') {
      this.products = Products;
    } else {
      this.products = [...Products].sort((a, b) => {
        const res = compare(a[column], b[column]);
        return direction === 'asc' ? res : -res;
      });
    }
  }
}
