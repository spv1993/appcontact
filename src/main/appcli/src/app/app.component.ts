import { Component } from '@angular/core';
import { AuthService } from './service/auth/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'appContact';
	
	constructor(private authService: AuthService) {
	}
	
	logout() {
		return this.authService.logout();
	}
}
