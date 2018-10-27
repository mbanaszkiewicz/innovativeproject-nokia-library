import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BookService } from '../../services/book/book.service';

@Component({
	selector: 'app-homepage',
	templateUrl: './homepage.component.html',
	styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

	books: any;
	fakeBooks = [
		{ 'id': 1, 'title': 'title 1', 'authorName': 'name1', 'authorSurname': 'surname1' },
		{ 'id': 2, 'title': 'title 2', 'authorName': 'name2', 'authorSurname': 'surname2' },
		{ 'id': 3, 'title': 'title 3', 'authorName': 'name2', 'authorSurname': 'surname2' }
	];
	newBook = { 'id': null, 'title': 'book ' + Math.floor(Math.random() * 100), 'authorName': 'name ' + Math.floor(Math.random() * 100), 'authorSurname': 'surname' + Math.floor(Math.random() * 100) };

	constructor(private http: HttpClient,
				private bookService: BookService) {
	}

	ngOnInit() {


		this.bookService.saveBook(this.newBook).subscribe();
		// this.bookService.updateBook(this.newBook, 1).subscribe();
		// this.bookService.removeBook(3).subscribe();

		this.bookService.getBooks().subscribe((response) => {
			this.books = response;
			console.log(this.books);
		});
	}

}
