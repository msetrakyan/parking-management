package com.parking.parkingmanagement.controller;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.booking.BookingCreateRequest;
import com.parking.parkingmanagement.model.booking.BookingDto;
import com.parking.parkingmanagement.service.booking.BookingService;
import com.parking.parkingmanagement.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<BookingDto>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/community")
    public ResponseEntity<List<BookingDto>> getAllBookingsByCommunity(@RequestParam Community community) {
        return ResponseEntity.ok(bookingService.getAllBookingsByCommunity(community));
    }

    @PostMapping
    public ResponseEntity<BookingDto> book(@RequestBody BookingCreateRequest bookingCreateRequest) {
        return ResponseEntity.ok(bookingService.book(bookingCreateRequest));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBooking(@RequestBody BookingCreateRequest bookingCreateRequest) {
            bookingService.cancelBooking(bookingCreateRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/myBookings")
    public ResponseEntity<List<BookingDto>> getBookingsByUsername() {
        return ResponseEntity.ok(bookingService.myBookings(CurrentUser.getUsername()));
    }

}