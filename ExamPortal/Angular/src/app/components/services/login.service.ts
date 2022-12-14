import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { baseUrl, token } from 'src/app/helper/api-constant';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  //public baseUrl="http://localhost:4200"

  constructor(private http:HttpClient) { }

  public loginUser(user:any,url:string){
    return this.http.post(`${baseUrl}${url}`,user)
  }

  //set token to localStorage
  public setToken(token:string){
    localStorage.setItem('token',token);
    return true;
  }

  //check user loged in ...if token present in localStorage means logged in
  public isLogin(){
    let token=localStorage.getItem('token');
    if(token==undefined || token=='' || token==null){
      return false;
    }
    else{
      return true;
    }
  }

  //logout user - removes token from local storage
  public logout(){
    localStorage.clear();
    return true;
  }

  public getToken(){
    return localStorage.getItem('token');
  }

  //setUserDetails
  public setuserDetails(user:any)
  {
    localStorage.setItem('email',user.email);
    localStorage.setItem('password',user.password);
  }

  //get role
  public getRole(email:string,url:string){
    console.log("rest call")
    JSON.stringify(email);
    return this.http.post(`${baseUrl}${url}`,email);
  }
}
