package com.nokia.library.nokiainnovativeproject.controllers;

import com.nokia.library.nokiainnovativeproject.DTOs.BookDetailsDTO;

import com.nokia.library.nokiainnovativeproject.exceptions.ResourceNotFoundException;
import com.nokia.library.nokiainnovativeproject.services.BookDetailsService;
import com.nokia.library.nokiainnovativeproject.utils.Mappings;
import com.nokia.library.nokiainnovativeproject.utils.MessageInfo;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;


@RestController
@RequiredArgsConstructor
@RequestMapping(Mappings.API_VERSION + Mappings.BOOK_DETAILS)
public class BookDetailsController {

	private final BookDetailsService bookDetailsService;

	@GetMapping(Mappings.GET_ALL)
	public ResponseEntity getAllBookDetails() {
		return MessageInfo.success(bookDetailsService.getAllBookDetails(), Arrays.asList("list of bookDetails"));
	}

	@GetMapping(Mappings.GET_ONE)
	public ResponseEntity getBookDetailsById(@PathVariable Long id) {
		return MessageInfo.success(bookDetailsService.getBookDetailsById(id), Arrays.asList("bookDetails of ID = " + id.toString()));
	}

	@PostMapping(Mappings.CREATE)
	public ResponseEntity createBookDetails(@RequestBody @Valid BookDetailsDTO bookDetailsDTO, BindingResult bindingResult) {
		ResponseEntity errors = MessageInfo.getErrors(bindingResult);
		if(errors != null)
			return errors;
		return MessageInfo.success(bookDetailsService.createBookDetails(bookDetailsDTO),Arrays.asList("bookDetails created successfully"));
	}

	@PostMapping(Mappings.UPDATE)
	public ResponseEntity updateBookDetails(@PathVariable Long id, @RequestBody @Valid BookDetailsDTO bookDetailsDTO, BindingResult bindingResult){
		ResponseEntity errors = MessageInfo.getErrors(bindingResult);
		if(errors != null)
			return errors;
		return MessageInfo.success(bookDetailsService.updateBookDetails(id, bookDetailsDTO),Arrays.asList("bookDetails updated successfully"));
	}

	@DeleteMapping(Mappings.REMOVE)
	public ResponseEntity deleteBookDetails(@PathVariable Long id) throws ResourceNotFoundException {
		bookDetailsService.deleteBookDetails(id);
		return MessageInfo.success(null, Arrays.asList("bookDetails with ID = " + id.toString() + " removed successfully"));
	}
}
