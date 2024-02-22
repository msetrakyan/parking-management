package com.parking.parkingmanagement.service.booking.impl;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.exception.exceptions.AlreadyBookedException;
import com.parking.parkingmanagement.exception.exceptions.ParkingNotFoundException;
import com.parking.parkingmanagement.mapper.booking.BookingMapper;
import com.parking.parkingmanagement.model.booking.BookingCreateRequest;
import com.parking.parkingmanagement.model.booking.BookingDto;
import com.parking.parkingmanagement.model.booking.BookingEntity;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import com.parking.parkingmanagement.repository.BookingRepository;
import com.parking.parkingmanagement.repository.ParkingRepository;
import com.parking.parkingmanagement.repository.UserRepository;
import com.parking.parkingmanagement.service.booking.BookingService;
import com.parking.parkingmanagement.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserRepository userRepository;
    private final ParkingRepository parkingRepository;


    @Override
    public BookingDto book(BookingCreateRequest bookingCreateRequest) {

        ParkingEntity parking = parkingRepository.findByParkingNumberAndCommunity(bookingCreateRequest.getParkingNumber(), CurrentUser.getCommunity()).orElseThrow(() -> new ParkingNotFoundException("Parking does not exist"));

        ParkingEntity byRowAndCommunity = parkingRepository.findByParkingNumberAndCommunity(parking.getParkingNumber(), parking.getCommunity()).orElseThrow(() -> new ParkingNotFoundException("Parking does not exist"));

        List<BookingEntity> list =
                bookingRepository
                .findBookingsByTimelineAndParkingId(bookingCreateRequest.getBookedFrom(), bookingCreateRequest.getBookedTo(), (long) byRowAndCommunity.getId());

        if(!list.isEmpty()) {
            throw new AlreadyBookedException("Parking is already booked");
        }

        BookingEntity bookingEntity = new BookingEntity();

        bookingEntity.setBookedFrom(bookingCreateRequest.getBookedFrom());
        bookingEntity.setBookedTo(bookingCreateRequest.getBookedTo());
        bookingEntity.setBookedBy(CurrentUser.getUsername());
        bookingEntity.setParking(byRowAndCommunity);
        bookingEntity.setCommunity(byRowAndCommunity.getCommunity());
        bookingEntity.setInProcess(false);


        return bookingMapper.toDto(bookingRepository.save(bookingEntity));
    }

    @Override
    public List<BookingDto> myBookings(String username) {
        return bookingMapper.toDtos(bookingRepository.findByUsername(username));
    }

    @Override
    public List<BookingDto> getAllBookingsByCommunity(Community community) {
        return bookingMapper.toDtos(bookingRepository.findAllByCommunity(community));
    }

    @Override
    public void cancelBooking(BookingCreateRequest bookingCreateRequest) {
        ParkingEntity parkingEntity = parkingRepository.findByParkingNumberAndCommunity(bookingCreateRequest.getParkingNumber(), CurrentUser.getCommunity()).orElseThrow(() -> new ParkingNotFoundException("Parking does not exist"));
        Optional<BookingEntity> byUsernameAndParkingNumber = bookingRepository.findByUsernameAndParkingNumber(CurrentUser.getUsername(), parkingEntity.getId());
        byUsernameAndParkingNumber.ifPresent(bookingRepository::delete);
    }

    @Override
    public List<BookingDto> getAllBookings() {
        return bookingMapper.toDtos(bookingRepository.findAll());
    }


    @Scheduled(fixedDelay = 10000)
    private void removeExpiredBookings() {
        List<BookingEntity> list = bookingRepository.findAllExpiredBookings();
        for (BookingEntity bookingEntity : list) {
            ParkingEntity parking = parkingRepository.findById(bookingEntity.getParking().getId()).orElseThrow(() -> new RuntimeException("Something went wrong"));
            if(parking.getUserEntity().equals(userRepository.findByUsername(bookingEntity.getBookedBy()).get())) {
                parking.setUserEntity(null);
                parkingRepository.save(parking);
            }
            bookingRepository.delete(bookingEntity);
        }
    }

    @Scheduled(fixedDelay = 10000)
    private void bookingsThatBegun() {

        List<BookingEntity> list = bookingRepository.findAllBookingsYetToBegin();

        for (BookingEntity bookingEntity : list) {

            bookingEntity.setInProcess(true);
            ParkingEntity byId = parkingRepository.findById(bookingEntity.getParking().getId()).orElseThrow(() -> new ParkingNotFoundException("Parking does not exist"));
            byId.setUserEntity(userRepository.findByUsername(bookingEntity.getBookedBy()).orElseThrow(() -> new UsernameNotFoundException("Username does not exist")));
            parkingRepository.save(byId);
            bookingRepository.save(bookingEntity);

        }



    }




}