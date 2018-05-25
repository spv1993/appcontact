import {Injectable} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest} from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { TOKEN_NAME } from './service/auth/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  
	constructor() {
	}
	
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
		const token = localStorage.getItem(TOKEN_NAME);
		let authReq = req;
		if(token) {
			authReq = req.clone({headers: req.headers.set('Authorization', token)});
		}
		console.log(authReq);
    return next.handle(authReq);
  }

}