import { Component, OnInit } from '@angular/core';
import { AuthService } from '../logic/auth.service';
import {Router, ActivatedRoute, RouterLink} from '@angular/router';
import {FormGroup, FormControl, Validators, ReactiveFormsModule} from '@angular/forms';


@Component({
  selector: 'app-register',
  standalone: true,
  templateUrl: './register.component.html',
  imports: [
    ReactiveFormsModule,
    RouterLink
  ],
  styleUrl: './register.component.scss'
})

export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.registerForm = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$')]),
      password: new FormControl('', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$')])
    });
  }

  public navigateToLogin() {
    // if returnUrl is set, navigate to login with returnUrl
    const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl');
    if (returnUrl) {
      this.router.navigate(['/login'], { queryParams: { returnUrl: returnUrl }});
    } else {
      this.router.navigate(['/login']);
    }
  }

  register() {
    if (this.registerForm.valid) {
      const email = this.registerForm.get('email')?.value;
      const password = this.registerForm.get('password')?.value;

      if (email && password) {
        this.authService.register(email, password).subscribe({
          next: () => {
            const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl');
            this.router.navigateByUrl(returnUrl || '/');
          },
          error: (err) => {
            this.errorMessage = err.message;
          }
        });
      }
    }
  }
}
