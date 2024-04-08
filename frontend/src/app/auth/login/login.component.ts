import { Component, OnInit } from '@angular/core';
import { AuthService } from '../logic/auth.service';
import {Router, ActivatedRoute, RouterLink} from '@angular/router';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';


@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  styleUrl: './login.component.scss'
})

export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$')]),
      password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$')])
    });
  }

  public navigateToRegister() {
    // if returnUrl is set, navigate to register with returnUrl
    const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl');
    if (returnUrl) {
      this.router.navigate(['/register'], { queryParams: { returnUrl: returnUrl }});
    } else {
      this.router.navigate(['/register']);
    }
  }

  login() {
    if (this.loginForm.valid) {
      const email = this.loginForm.get('email')?.value;
      const password = this.loginForm.get('password')?.value;

      if (email && password) {
        this.authService.login(email, password).subscribe({
          next: () => {
            const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl');
            this.router.navigateByUrl(returnUrl || '/');
          },
          error: (err) => {
            if  (err.status === 401) {
              this.errorMessage = 'Invalid credentials';
            } else if (err.status === 500) {
              this.errorMessage = 'Server error';
            } else {
              this.errorMessage = 'Unknown error';
            }
          }
        });
      }
    }
  }
}
