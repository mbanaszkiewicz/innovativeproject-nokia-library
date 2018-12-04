import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmailMessage } from '../../models/EmailMessage';
import { MatDialogRef } from '@angular/material';
import { RestService } from '../../services/rest/rest.service';

@Component({
	selector: 'app-contact-us',
	templateUrl: './contact-us.component.html',
	styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit {

	contactParams: FormGroup;
	sendingEmail = false;
	categories = ['Report a bug', 'Request new feature', 'I don\'t like...', 'Other'];
	sendingFailed = false;
	errorMessage;

	constructor(private formBuilder: FormBuilder,
				public dialogRef: MatDialogRef<ContactUsComponent>,
				private http: RestService) {
	}

	ngOnInit() {
		this.initForm();
	}

	initForm() {
		this.contactParams = this.formBuilder.group({
			category: ['', Validators.required],
			subject: ['', Validators.required],
			message: ['', Validators.required]
		});
	}

	sendEmail(contactParams: FormGroup) {
		this.sendingEmail = true;
		this.sendingFailed = false;
		const email = new EmailMessage(contactParams.value.category + ': ' + contactParams.value.subject, contactParams.value.message);

		this.http.save('email', email).subscribe((response) => {
			if (response.success === true) {
				this.emailSent();
			} else if (response.success === false) {
				this.sendingFailed = true;
				this.errorMessage = response.message[0];
			}
			this.sendingEmail = false;
		});
	}

	emailSent(): void {
		this.dialogRef.close();
		// TODO: pop up a snackbar when finished
	}
}
