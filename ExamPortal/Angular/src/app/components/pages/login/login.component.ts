import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLogin={
    password:"",
    email:""
  }

  constructor() { }

  ngOnInit(): void {
  }

  submitRegistration(form:any){
    if(form.status=="VALID"){
      console.log(this.userLogin,"userLogin");
    }
    else{
      console.log("Invalid credebtials")
    }
  }
}
