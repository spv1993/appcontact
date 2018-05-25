import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { User } from '../../model/user';

@Injectable({ providedIn: 'root' })
export class UserService {

  public API_URL = '//localhost:8080';
  public USER_API_URL = this.API_URL + '/api/users';

  constructor(private http: HttpClient) {
	}
	
	getUsers(): Observable<User[]> {
		return this.http.get<User[]>(this.USER_API_URL);
  }
	
	get(id: string): Observable<User> {
    return this.http.get<User>(this.USER_API_URL + '/' + id);
  }
	
	save(user: User): Observable<User> {
    let result: Observable<User>;
	if(user.id) {
	  result = this.http.put<User>(this.USER_API_URL + '/' + user.id, user);
	} else {
	  result = this.http.post<User>(this.USER_API_URL, user);
	}
    return result;
  }
	
	remove(id: number): Observable<User> {
    return this.http.delete<User>(this.USER_API_URL + '/' + id);
  }
}