import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { environment } from '../../../environments/environment';

import { ApiResponse } from '../models/api-response';
import { LoginRequest } from '../models/login-request';
import { LoginResponse } from '../models/login-response';
import { RegisterRequest } from '../models/register-request';
import { UserResponse } from '../models/user-response';
import { RefreshTokenRequest } from '../models/refresh-token-request';
import { LogoutResponse } from '../models/logout-response';
import { RefreshTokenResponse } from '../models/refresh-token-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private http = inject(HttpClient);

  private readonly API = environment.apiUrl;

  constructor() {}

  // Register
  register(request: RegisterRequest): Observable<ApiResponse<UserResponse>> {

    return this.http.post<ApiResponse<UserResponse>>(
      `${this.API}/auth/register`,
      request
    );
  }

  // Login
  login(request: LoginRequest): Observable<ApiResponse<LoginResponse>> {

    return this.http.post<ApiResponse<LoginResponse>>(
      `${this.API}/auth/login`,
      request
    );
  }

  // Current User
  getCurrentUser(): Observable<ApiResponse<UserResponse>> {

    return this.http.get<ApiResponse<UserResponse>>(
      `${this.API}/users/me`
    );
  }

  // Refresh Token
  refreshToken(request: RefreshTokenRequest)
  : Observable<ApiResponse<RefreshTokenResponse>> {

    return this.http.post<ApiResponse<RefreshTokenResponse>>(
      `${this.API}/auth/refresh`,
      request
    );
  }

  // Logout
  logout(): Observable<ApiResponse<LogoutResponse>> {

    return this.http.post<ApiResponse<LogoutResponse>>(
      `${this.API}/auth/logout`,
      {}
    );
  }

}