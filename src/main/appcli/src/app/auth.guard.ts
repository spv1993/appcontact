import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from './service/auth/auth.service';

@Injectable()
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router, 
    private authService: AuthService) {}

  canActivate() {		
    if (this.authService.isTokenValid()) {
      return true;
    }

    this.router.navigate(['/signin']);
    return false;
  }

}