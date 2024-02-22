package com.parking.parkingmanagement.mapper.booking;


import com.parking.parkingmanagement.model.booking.BookingDto;
import com.parking.parkingmanagement.model.booking.BookingEntity;

import java.util.List;

public interface BookingMapper {


    BookingEntity toEntity(BookingDto bookingDto);

    BookingDto toDto(BookingEntity bookingEntity);

    List<BookingDto> toDtos(List<BookingEntity> bookingEntities);


}