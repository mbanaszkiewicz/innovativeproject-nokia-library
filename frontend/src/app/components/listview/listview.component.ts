import { AfterViewInit, Component, OnInit } from '@angular/core';
import { BookDetailsService } from '../../services/book-details/book-details.service';
import { BookDetails } from '../../models/database/entites/BookDetails';
import { RestService } from '../../services/rest/rest.service';
import { MessageInfo } from '../../models/MessageInfo';

@Component({
	selector: 'app-listview',
	templateUrl: './listview.component.html',
	styleUrls: ['./listview.component.css']
})
export class ListviewComponent implements OnInit, AfterViewInit {

	books: any;
	allBooks: BookDetails[] = [];
	errorMessage: any;

	newBookDTO = {
		'coverPictureUrl': 'https://itbook.store/img/books/9781491985571.png',
		'dateOfPublication': new Date(),
		'description': 'desc',
		'isbn': '12312312312',
		'tableOfContents': 'string',
		'title': 'Book ' + Math.floor(Math.random() * 100)
	};

	constructor(private bookDetailsService: BookDetailsService, private http: RestService) {
	}

	ngOnInit() {

	}

	ngAfterViewInit(): void {
		this.getBooks();
	}


	async getBooks(id?: number) {
		this.books = await this.bookDetailsService.getBooks(id)
		.catch((err) => {
			console.log(err.message);
			this.errorMessage = err;
		});

		const response: MessageInfo = await this.http.getAll('bookDetails/getAll');
		this.allBooks = response.object.sort().reverse();

		console.log(this.books);
		console.log(this.allBooks);
	}

}