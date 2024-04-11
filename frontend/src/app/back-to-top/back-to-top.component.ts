import { Component, HostListener } from '@angular/core';
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-back-to-top',
  standalone: true,
  imports: [
    NgIf
  ],
  templateUrl: './back-to-top.component.html',
  styleUrl: './back-to-top.component.scss'
})
export class BackToTopComponent {
  showButton = false;

  @HostListener('window:scroll')
  onWindowScroll(): void {
    this.showButton = window.pageYOffset > 100;
  }

  scrollToTop(): void {
    window.scrollTo({ top: 0, behavior: 'smooth' });
  }
}
