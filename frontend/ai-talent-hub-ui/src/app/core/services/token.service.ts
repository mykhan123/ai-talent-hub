import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private readonly ACCESS_TOKEN = 'access_token';
  private readonly REFRESH_TOKEN = 'refresh_token';

  constructor() { }

  // Save Tokens
  saveTokens(accessToken: string, refreshToken: string): void {
    localStorage.setItem(this.ACCESS_TOKEN, accessToken);
    localStorage.setItem(this.REFRESH_TOKEN, refreshToken);
  }

  // Get Access Token
  getAccessToken(): string | null {
    return localStorage.getItem(this.ACCESS_TOKEN);
  }

  // Get Refresh Token
  getRefreshToken(): string | null {
    return localStorage.getItem(this.REFRESH_TOKEN);
  }

  // Remove Tokens
  clearTokens(): void {
    localStorage.removeItem(this.ACCESS_TOKEN);
    localStorage.removeItem(this.REFRESH_TOKEN);
  }

  // Check Login
  isLoggedIn(): boolean {
    return this.getAccessToken() != null;
  }

  saveAccessToken(token:string):void{
    localStorage.setItem(this.ACCESS_TOKEN,token);
}

saveRefreshToken(token:string):void{
    localStorage.setItem(this.REFRESH_TOKEN,token);
}
}