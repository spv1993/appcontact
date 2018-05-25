import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

import { AuthService } from '../service/auth/auth.service';
import { User } from '../model/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

	user: any = {};

  constructor(private router: Router,
              private authService: AuthService) { }

  ngOnInit() {
		this.user.username = '';
		this.user.password = '';
  }

	signup(event, user: User) {
		event.preventDefault();
		this.authService.signup(user);
	}
		
	login(event) {
		event.preventDefault();
		this.router.navigate(['signin']);
	}
}
