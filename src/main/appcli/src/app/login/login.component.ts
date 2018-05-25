import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { AuthService } from '../service/auth/auth.service';
import { User } from '../model/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

	user: any = {};

  constructor(private router: Router,
              private authService: AuthService) {
	}

  ngOnInit() {
		this.user.username = '';
		this.user.password = '';
  }
	
	signin(event, user: User) {
		event.preventDefault();
		this.authService.signin(user);
		
		
	}
	
	signup(event) {
    event.preventDefault();
    this.router.navigate(['signup']);
  }
}
