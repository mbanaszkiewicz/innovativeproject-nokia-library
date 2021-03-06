import { Component, OnInit } from '@angular/core';
import { BookDetailsService } from '../../../services/book-details/book-details.service';
import { BookDetails } from '../../../models/database/entites/BookDetails';
import { RestService } from '../../../services/rest/rest.service';
import { MessageInfo } from '../../../models/MessageInfo';

@Component({
	selector: 'app-listview',
	templateUrl: './listview.component.html',
	styleUrls: ['./listview.component.scss']
})
export class ListviewComponent implements OnInit {

	booksAll: BookDetails[] = [];
	books: BookDetails[] = [];
	listIsLoading = false;
	hideUnavailable = false;
	value = '';

	constructor(private bookDetailsService: BookDetailsService, private http: RestService) {
	}

	ngOnInit() {
		this.getBooksDetails();
	}

	searchBooks(val) {
		this.books = this.booksAll.filter((b) => {
			return (b.title.toLowerCase().includes(val.toLowerCase())
				|| JSON.stringify(b.authors).toLowerCase().includes(val.toLowerCase())
				|| JSON.stringify(b.categories).toLowerCase().includes(val.toLowerCase()));
		});
	}

	async getBooksDetails() {
		this.listIsLoading = true;
		const response: MessageInfo = await this.http.getAll('bookDetails/getAll/available');
		this.booksAll = response.object.sort().reverse();
		this.listIsLoading = false;
		this.books = this.booksAll;
	}

}
