import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Contact } from '../../model/contact';

@Injectable({ providedIn: 'root' })
export class ContactService {

  public API_URL = '//localhost:8080';
  public CONTACT_API_URL = this.API_URL + '/api/contacts';
	
  constructor(private http: HttpClient) {
  }
  
  getContacts(): Observable<Contact[]> {
		return this.http.get<Contact[]>(this.CONTACT_API_URL);
  }
  
  get(id: string): Observable<Contact> {
    return this.http.get<Contact>(this.CONTACT_API_URL + '/' + id);
  }
  
  save(contact: Contact): Observable<Contact> {
    let result: Observable<Contact>;
	if(contact.id) {
	  result = this.http.put<Contact>(this.CONTACT_API_URL + '/' + contact.id, contact);
	} else {
	  result = this.http.post<Contact>(this.CONTACT_API_URL, contact);
	}
    return result;
  }
  
  remove(contactId: number): Observable<Contact> {
    return this.http.delete<Contact>(this.CONTACT_API_URL + '/' + contactId);
  }
}
