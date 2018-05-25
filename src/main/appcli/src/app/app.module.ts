import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { 
  MatButtonModule, 
  MatCardModule, 
  MatInputModule, 
  MatListModule, 
  MatToolbarModule, 
  MatSidenavModule 
} from '@angular/material';

import { AppComponent } from './app.component';
import { AuthGuard } from './auth.guard';
import { AuthInterceptor } from './auth.interceptor';
import { ContactService } from './service/contact/contact.service';
import { ContactListComponent } from './component/contact/contact-list/contact-list.component';
import { ContactEditComponent } from './component/contact/contact-edit/contact-edit.component';
import { UserService } from './service/user/user.service';
import { UserListComponent } from './component/user/user-list/user-list.component';
import { UserEditComponent } from './component/user/user-edit/user-edit.component';
import { AuthService } from './service/auth/auth.service';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';


const appRoutes: Routes = [
	{ path: '', 						redirectTo: '/contacts', pathMatch: 'full' },
	
	{ path: 'signin', 			component: LoginComponent },
	{ path: 'signup', 			component: SignupComponent },
  
	{ path: 'contacts', 		component: ContactListComponent, canActivate: [AuthGuard] },
  { path: 'contacts/new', component: ContactEditComponent, canActivate: [AuthGuard] },
  { path: 'contacts/:id', component: ContactEditComponent, canActivate: [AuthGuard] },
	
	{ path: 'users', 				component: UserListComponent, canActivate: [AuthGuard] },
	{ path: 'users/new', 		component: UserEditComponent, canActivate: [AuthGuard] },
	{ path: 'users/:id', 		component: UserEditComponent, canActivate: [AuthGuard] }
];

@NgModule({
  declarations: [
    AppComponent,
    ContactListComponent,
    ContactEditComponent,
    UserListComponent,
    UserEditComponent,
    LoginComponent,
    SignupComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
		BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
		MatSidenavModule,
		FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
		ContactService, 
		UserService,
		AuthService,
		{
			provide: HTTP_INTERCEPTORS,
			useClass: AuthInterceptor,
			multi: true
		},
		AuthGuard
	],
  bootstrap: [AppComponent]
})
export class AppModule { }
