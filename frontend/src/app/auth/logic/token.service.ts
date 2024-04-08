import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private _tokenKey: string = 'auth_token';

  constructor() { }

  storeToken(token: string): void {
    localStorage.setItem(this._tokenKey, token);
  }

  loadToken(): string | null {
    return localStorage.getItem(this._tokenKey);
  }

  removeToken(): void {
    localStorage.removeItem(this._tokenKey);
  }

  hasToken(): boolean {
    return !!this.loadToken();
  }

  getToken(): string {
    return this.loadToken() || '';
  }

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (token) {
      return true;
    } else {
      return false;
    }
  }
}
