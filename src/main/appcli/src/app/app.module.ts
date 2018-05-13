import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
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
import { ContactService } from './service/contact/contact.service';
import { ContactListComponent } from './component/contact/contact-list/contact-list.component';
import { ContactEditComponent } from './component/contact/contact-edit/contact-edit.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/contacts', pathMatch: 'full' },
  {
    path: 'contacts',
    component: ContactListComponent
  },
  {
    path: 'contacts/new',
    component: ContactEditComponent
  },
  {
    path: 'contacts/:id',
    component: ContactEditComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    ContactListComponent,
    ContactEditComponent
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
  providers: [ContactService],
  bootstrap: [AppComponent]
})
export class AppModule { }
