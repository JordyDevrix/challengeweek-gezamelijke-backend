import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CookieService {


  constructor() { }

  public setCookie(name: string, value: string, days: number): void {
    let expires = "";
    if (days) {
      let date = new Date();
      date.setTime(date.getTime() + (days*24*60*60*1000));
      expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + expires + "; path=/";
  }

  public getCookie(name: string): any {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for(let i=0;i < ca.length;i++) {
      let c = ca[i];
      while (c.charAt(0) === ' ') c = c.substring(1,c.length);
      if (c.indexOf(nameEQ) === 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
  }

  public eraseCookie(name: string): void {
    document.cookie = name + '=; Max-Age=-99999999;';
  }

  public deleteAllCookies(): void {
    let cookies = document.cookie.split(";");
    for (let i = 0; i < cookies.length; i++) {
      let cookie = cookies[i];
      let eqPos = cookie.indexOf("=");
      let name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
      document.cookie = name + '=; Max-Age=-99999999;';
    }
  }

  public checkCookie(name: string): boolean {
    let cookie = this.getCookie(name);
    return cookie !== null;
  }

}
