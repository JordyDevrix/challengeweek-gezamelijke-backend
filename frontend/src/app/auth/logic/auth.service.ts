import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';
import { AuthResponse } from './auth-response.model';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _apiUrl: string = 'http://s1149771.student.inf-hsleiden.nl:29771/api/auth';

  constructor(private http: HttpClient, private tokenService: TokenService) { }

  login(email: string, password: string) {
    return this.http.post<AuthResponse>(`${this._apiUrl}/login`, { email, password })
      .pipe(tap((res: AuthResponse) => this.tokenService.storeToken(res.token)));
  }

  register(email: string, password: string) {
    return this.http.post<AuthResponse>(`${this._apiUrl}/register`, { email, password })
      .pipe(tap((res: AuthResponse) => this.tokenService.storeToken(res.token)));
  }

  isLoggedIn(): boolean {
    return this.tokenService.isLoggedIn();
  }

  logout(): void {
    this.tokenService.removeToken();
  }
}
