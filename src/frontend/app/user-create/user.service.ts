import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient) {

  }

  login(loginRequest: any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/auth/login`, loginRequest);
  }

  register(registerRequest: any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}/auth/register`, registerRequest);
  }

  confirmRegistration(token: string): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/auth/confirm?token=${token}`);
  }

  requestPasswordReset(email: string): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/auth/request-password-reset`, { email });
  }

  resetPassword(token: string, newPasswordRequest: any): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/auth/reset-password?token=${token}`, newPasswordRequest);
  }
}
