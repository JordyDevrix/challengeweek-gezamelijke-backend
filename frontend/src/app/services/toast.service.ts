import { Injectable } from '@angular/core';
declare var bootstrap: any;

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor() { }

  showToast(toastId: string): void {
    var toastEl = document.getElementById(toastId);
    var toast = new bootstrap.Toast(toastEl);
    toast.show();
  }

}
