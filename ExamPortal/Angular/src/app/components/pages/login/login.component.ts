import { Component, OnInit } from '@angular/core';
import { roleDetails, token } from 'src/app/helper/api-constant';
import { LoginService } from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  userLogin = {
    password: "",
    email: ""
  }

  constructor(private loginServ: LoginService) { }

  ngOnInit(): void {
  }

  loginUserRestCall() {
    this.loginServ.loginUser(this.userLogin, token).subscribe(
      (data) => {
        alert("Success");
        var tokenRX: any;
        tokenRX = data;
        if (tokenRX.token == undefined || tokenRX.token == '' || tokenRX.token == null) {

          console.log(tokenRX.token, "adter login invalid user");
        }
        else {
          this.loginServ.setToken(tokenRX.token);
          this.loginServ.setuserDetails(this.userLogin);
          this.getUserRoles();

        }
        console.log(tokenRX.token, "adter login");

      },
      (error) => {
        console.error(error);
      }
    )
  }

  getUserRoles() {
    this.loginServ.getRole(this.userLogin.email, roleDetails).subscribe(
      (data) => {
        console.log("roles", data);
      },
      (error) => {
        console.error(error);
      }
    )
  }

  submitRegistration(form: any) {
    if (form.status == "VALID") {
      console.log(this.userLogin, "userLogin");
      this.loginUserRestCall();
      
    }
    else {
      console.log("Invalid credebtials")
    }
  }
}


