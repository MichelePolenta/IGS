import { Component} from '@angular/core';
import {Router} from '@angular/router';
import {AbstractControl, FormBuilder, Validators, FormGroup} from '@angular/forms';
import { Persona } from 'src/app/Models/persona';
import { SignupService } from 'src/app/Services/signup/signup.service';


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent{
  errorMessage = '';
  hidePass = true;
  hideConfPass = true;
  firstFormGroup! : FormGroup;
  secondFormGroup! : FormGroup;
  selectedRole: string | undefined;
  isLinear = true;

  constructor(private formBuilder: FormBuilder, private signupService: SignupService, private router: Router) {
    this.firstFormGroup = this.formBuilder.group({
      email: ['', [Validators.required]],
      pass: ['', [Validators.required]],
      passConf:['',[Validators.required]],
    } , {validators : this.passwordMatchValidator});
    this.secondFormGroup = this.formBuilder.group({
      name: ['', Validators.required],
      surname: ['',Validators.required],
      date:['',Validators.required],
    });

    this.firstFormGroup.get('role')?.valueChanges.subscribe((value: string) => {
      this.selectedRole = value;
    });
  }

  get pass() { return this.firstFormGroup.get('pass'); }
	get passConf() { return this.firstFormGroup.get('passConf'); }

    passwordMatchValidator(control: AbstractControl){
    const pass = control.get('pass')?.value;
		const confPass = control.get('passConf')?.value
		if(pass && confPass && pass != confPass) {
      return {'mismatch' : true};
		}
		return null;
  }

  getUserToFrom(){
    return {
      mail: this.firstFormGroup.get('email')?.value,
      password: this.firstFormGroup.get('pass')?.value,
      ruolo: this.getRole("CONTRAUT"),
      comune: "Ancona",
      nome: this.secondFormGroup.get('name')?.value,
      cognome: this.secondFormGroup.get('surname')?.value,
      dataDiNascita: this.getFormattedDate(),
		};
  }

  getFormattedDate(): string {
    const selectedDate = this.secondFormGroup.get('date')?.value;
    if (selectedDate) {
      const year = selectedDate.getFullYear();
      const month = String(selectedDate.getMonth() + 1).padStart(2, '0');
      const day = String(selectedDate.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    }
    return '';
  }

  getRole = (role: string): string => {
    return 'CONTRAUT';
  };

  updateErrorMessage(){
    this.errorMessage = '';
  }

  togglePasswordVisibility(field: String){
    if(field == 'pass'){
      this.hidePass = !this.hidePass;
    }else if(field == 'confPass'){
      this.hideConfPass = !this.hideConfPass;
    }
  }

  showToggle(){

  }

  signup(){
    const user: Persona = this.getUserToFrom();
    console.log(user),
    this.signupService.signup(user)
			.subscribe({
				next: (result) => {
          window.location.assign("/login");
        },
				error: (error) => {
					console.error('Signup failed:', error);
          this.errorMessage = error;
				}
			});
  }
}

