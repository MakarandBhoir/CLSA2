import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable()
export class StudentHttpInterceptor implements HttpInterceptor {
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let requestUrl: string = req.url;
        //alert(requestUrl);
        if (requestUrl.endsWith('login.do')) {
            return next.handle(req);
        } else {
            //  alert("Adding Token");
            let token = sessionStorage.getItem('username');
            let modified_req = req.clone({ setHeaders: { 'Authorization': `Bearer ${token}` } });
            //alert(`Bearer ${token}`);
            return next.handle(modified_req);
        }
    }
}