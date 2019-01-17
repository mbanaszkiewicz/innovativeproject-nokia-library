package com.nokia.library.nokiainnovativeproject.controllers;

import com.nokia.library.nokiainnovativeproject.DTOs.ReservationDTO;
import com.nokia.library.nokiainnovativeproject.services.ReservationService;
import com.nokia.library.nokiainnovativeproject.utils.MessageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;

import static com.nokia.library.nokiainnovativeproject.utils.Mappings.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(API_VERSION)
public class ReservationController {

	private final ReservationService reservationService;

	@GetMapping(RESERVATIONS + GET_ALL)
	public ResponseEntity getAllReservations() {
		return MessageInfo.success(reservationService.getAllReservations(), Arrays.asList("Full list of book reservations"));
	}

	@GetMapping(RESERVATIONS + GET_ONE)
	public ResponseEntity getReservationById(@PathVariable Long id) {
		return MessageInfo.success(reservationService.getReservationById(id), Arrays.asList("Reservation with ID = " + id.toString()));
	}

	@GetMapping(RESERVATIONS + USER)
	public ResponseEntity getReservationsByUser() {
		return MessageInfo.success(reservationService.getReservationsByUser(), Arrays.asList("Reservations with UserID = "));
	}

	@GetMapping(BOOKS + RESERVATIONS + GET_ONE)
	public ResponseEntity getReservationsByBookId(@PathVariable Long id) {
		return MessageInfo.success(reservationService.getReservationsByBookId(id), Arrays.asList("Reservations with BookID = " + id.toString()));
	}

	@PostMapping(RESERVATIONS + CREATE)
	public ResponseEntity createReservation(@RequestBody @Valid ReservationDTO reservationDTO, BindingResult bindingResult) {
		MessageInfo.validateBindingResults(bindingResult);
		return MessageInfo.success(reservationService.createReservation(reservationDTO), Arrays.asList("Reservation created successfully"));
	}

	@DeleteMapping(RESERVATIONS + REMOVE)
	public ResponseEntity deleteReservation(@PathVariable Long id) {
		reservationService.deleteReservation(id);
		return MessageInfo.success(null, Arrays.asList("Reservation with ID = " + id.toString() + " removed successfully"));
	}
}
