export class BookToOrderDTO {
	isbn: string;
	title: string;

	constructor(isbn: string, title: string) {
		this.isbn = isbn;
		this.title = title;
	}
}