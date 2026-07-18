import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { inject } from '@angular/core';



import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';
import { TokenService } from '../../../core/services/token.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatFormFieldModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class Login {
private fb = inject(FormBuilder);
private authService = inject(AuthService);
private tokenService = inject(TokenService);
private router = inject(Router);

loginForm = this.fb.group({
  email: ['', [Validators.required, Validators.email]],
  password: ['', [Validators.required, Validators.minLength(6)]]
});

login(): void {

  if (this.loginForm.invalid) {
    this.loginForm.markAllAsTouched();
    return;
  }

  this.authService.login(this.loginForm.value as any).subscribe({

    next: (response) => {

      console.log(response);

      this.tokenService.saveAccessToken(response.data.accessToken);

      this.tokenService.saveRefreshToken(response.data.refreshToken);

      this.router.navigate(['/dashboard']);

    },

    error: (error) => {

      console.error(error);

      alert('Invalid Email or Password');

    }

  });

}
}