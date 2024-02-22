package com.parking.parkingmanagement.repository;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.booking.BookingEntity;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    @Query(value = "SELECT * \n" +
            "FROM bookings \n" +
            "WHERE (booked_from, booked_to) OVERLAPS (:requestedFrom, :requestedTo)\n" +
            "  AND parking_id = :parkingId",
    nativeQuery = true)
    List<BookingEntity> findBookingsByTimelineAndParkingId(@Param("requestedFrom")LocalDateTime requestedFrom, @Param("requestedTo")LocalDateTime requestedTo, @Param("parkingId")Long parkingId);

    List<BookingEntity> findByParking(ParkingEntity parkingEntity);

    @Query(value = "Select * from bookings where booked_by = :username",
        nativeQuery = true
    )
    List<BookingEntity> findByUsername(@Param("username") String username);

    @Query(value = "Select * from bookings where booked_by = :username and parking_id = :parkingId", nativeQuery = true)
    Optional<BookingEntity> findByUsernameAndParkingNumber(@Param("username")String username,@Param("parkingId") Integer parkingNumber);


    List<BookingEntity> findAllByCommunity(Community community);


    @Query(value = "Select * from bookings where booked_to < NOW() ", nativeQuery = true)
    List<BookingEntity> findAllExpiredBookings();


    @Query(value = "Select * from bookings where booked_from < NOW() and in_process = false", nativeQuery = true)
    List<BookingEntity> findAllBookingsYetToBegin();


}
