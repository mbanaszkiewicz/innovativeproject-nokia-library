import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ContactUsComponent } from '../../../components/contact-us/contact-us.component';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Router } from '@angular/router';

@Component({
	selector: 'app-navbar',
	templateUrl: './navbar.component.html',
	styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

	placeHolder = 'Search for a book...';
	searchParams: FormGroup;

	isAuth = false;
	role_admin = false;
	role_employee = false;
	loggedAs = '';

	@Output() changedTheme = new EventEmitter<string>();

	constructor(public dialog: MatDialog,
				private formBuilder: FormBuilder,
				private authService: AuthService,
				private router: Router) {}

	ngOnInit() {
		this.initForm();
		this.initAuthVariables();
	}

	async initAuthVariables() {

	  console.log('before init');

     this.authService.isDataActual().then(() => {
      this.isAuth = this.authService.isAuthenticated();
      this.role_admin = this.authService.isAdmin();
      this.role_employee = this.authService.isUser();
      this.loggedAs = this.authService.getUsername();

      console.log('after init');

    });
  }

	initForm() {
		this.searchParams = this.formBuilder.group({
			searchValue: ''
		});
	}

	login() {
		this.router.navigateByUrl('/login');
	}

	logout() {
		this.authService.logoutUser();
	}

	register() {
		this.router.navigateByUrl('/register');
	}


	search(searchParams: any) {
		console.log(searchParams.value.searchValue);
	}

	openDialog() {
		const dialogRef = this.dialog.open(ContactUsComponent, {});
	}

	checkPlaceHolder() {
		if (this.placeHolder) {
			this.placeHolder = null;
			return;
		} else {
			this.placeHolder = 'Search for a book...';
			return;
		}
	}

	changeTheme() {
		if (localStorage.getItem('theme') == 'light') {
			this.changedTheme.emit('dark');
		} else if (localStorage.getItem('theme') == 'dark') {
			this.changedTheme.emit('light');
		} else {
			this.changedTheme.emit('dark');
		}
		// if (localStorage.getItem('theme') == 'light') {
		// 	localStorage.setItem('theme', 'dark');
		// } else if (localStorage.getItem('theme') == 'dark') {
		// 	localStorage.setItem('theme', 'light');
		// } else {
		// 	localStorage.setItem('theme', 'dark');
		// }
	}
}
