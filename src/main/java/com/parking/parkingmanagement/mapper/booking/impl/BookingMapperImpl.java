package com.parking.parkingmanagement.mapper.booking.impl;

import com.parking.parkingmanagement.mapper.booking.BookingMapper;
import com.parking.parkingmanagement.model.booking.BookingDto;
import com.parking.parkingmanagement.model.booking.BookingEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingMapperImpl implements BookingMapper {

    @Override
    public BookingEntity toEntity(BookingDto bookingDto) {
        BookingEntity entity = new BookingEntity();
        entity.setBookedBy(bookingDto.getBookedBy());
        entity.setBookedFrom(bookingDto.getBookedFrom());
        entity.setBookedTo(bookingDto.getBookedTo());
        return entity;
    }

    @Override
    public BookingDto toDto(BookingEntity bookingEntity) {
        return BookingDto.builder()
                .parkingNumber(bookingEntity.getParking().getParkingNumber())
                .community(bookingEntity.getCommunity())
                .bookedBy(bookingEntity.getBookedBy())
                .bookedTo(bookingEntity.getBookedTo())
                .bookedFrom(bookingEntity.getBookedFrom())
                .build();
    }

    @Override
    public List<BookingDto> toDtos(List<BookingEntity> bookingEntities) {
        return bookingEntities.stream().map(this::toDto).toList();
    }

}
