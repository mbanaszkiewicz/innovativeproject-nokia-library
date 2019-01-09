package com.nokia.library.nokiainnovativeproject.utils;

public class Mappings {
	public static final String API_VERSION = "/api/v1";

	public static final String PORT_AUTOTESTS = "http://localhost:8080";
	public static final String PORT_DEVELOPMENT = "http://localhost:8081";
	public static final String PORT_PRODUCTION = "http://localhost:8082";

	// method mappings
	public static final String GET_ALL = "/getAll";
	public static final String GET_ONE = "/getOne/{id}";
	public static final String CREATE = "/create";
	public static final String UPDATE = "/update/{id}";
	public static final String REMOVE = "/remove/{id}";

	// controller mappings
	public static final String BOOK_DETAILS = "/bookDetails";
	public static final String BOOK_CATEGORY = "/bookCategory";
	public static final String BOOK_AUTHOR = "/author";
	public static final String BOOK_REVIEW = "/review";
	public static final String BOOK_COPY = "/bookCopy";
	public static final String BOOK_TO_ORDER = "/bookToOrder";

	public static final String USERS = "/users";
	public static final String LIBRARY = "/library";
	public static final String BOOKS = "/books";
    public static final String RESERVATIONS = "/reservations" ;
	public static final String RENTALS = "/rentals";

	public static final String PICTURES = "/pictures";
	public static final String UPLOAD = "/upload";

	public static final String EMAIL = "/email";

	public static final String AUTOCOMPLETION = "/autocompletion";

	public static final String USER = "/user";

    public static final String HANDOVER = "/handover/{id}" ;
    public static final String RETURN = "/return/{id}" ;

	public static final String TAKE_ADMIN = "/takeAdmin/{id}";
	public static final String ASSIGN_ADMIN = "/assignAdmin/{id}";


}
