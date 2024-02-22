package com.parking.parkingmanagement.model.booking;

import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private ParkingEntity parking;

    @Column(nullable = false)
    private LocalDateTime bookedFrom;

    @Column(nullable = false)
    private LocalDateTime bookedTo;

    @Column(nullable = false)
    private String bookedBy;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Community community;


    @ColumnDefault("false")
    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean inProcess;



}