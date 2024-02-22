package com.parking.parkingmanagement.service.booking;



import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.booking.BookingCreateRequest;
import com.parking.parkingmanagement.model.booking.BookingDto;

import java.util.List;

public interface BookingService {


    List<BookingDto> getAllBookings();

    BookingDto book(BookingCreateRequest bookingCreateRequest);

    List<BookingDto> myBookings(String username);

    void cancelBooking(BookingCreateRequest bookingCreateRequest);

    List<BookingDto> getAllBookingsByCommunity(Community community);




}