import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import * as jwt_decode from 'jwt-decode';

import { User } from '../../model/user';

export const TOKEN_NAME: string = 'access_token';

@Injectable({ providedIn: 'root' })
export class AuthService {

	private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  public API_URL = '//localhost:8080';
  public SIGNIN_API_URL = this.API_URL + '/auth/get';
	public SIGNUP_API_URL = this.API_URL + '/auth/create';
	public LOGOUT_API_URL = this.API_URL + '/auth/logout';
	
  constructor(private http: HttpClient,
							private router: Router) { 
	}
	
	getToken(): string {
    return localStorage.getItem(TOKEN_NAME);
  }
	
	setToken(token: string): void {
    localStorage.setItem(TOKEN_NAME, token);
  }
	
	removeToken(): void {
    localStorage.removeItem(TOKEN_NAME);
  }

  isTokenValid(token?: string): boolean {
    if(this.getToken()) {
			return true;
		}
		return false;
  }

  signin(user) {
    return this.http.post<any>(this.SIGNIN_API_URL, JSON.stringify(user), { headers: this.headers })
			.subscribe(response => {
				this.setToken(response.accessToken);
				this.router.navigate(['contacts']);
				}, error => {
					console.log(error);
				});
  }
	
	signup(user) {
		return this.http.post<any>(this.SIGNUP_API_URL, JSON.stringify(user), { headers: this.headers })
			.subscribe(response => {
				this.setToken(response.accessToken);
				this.router.navigate(['contacts']);
			}, error => {
				console.log(error);
			});
	}
	
	logout() {
		return this.http.get<any>(this.LOGOUT_API_URL, { headers: this.headers })
			.subscribe(response => {
				this.removeToken();
				this.router.navigate(['signin']);
			}, error => {
				console.log(error);
			});
		
	}
	
}
