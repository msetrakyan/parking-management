package com.parking.parkingmanagement.model.parking;


import com.parking.parkingmanagement.constants.Community;
import com.parking.parkingmanagement.model.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.stereotype.Component;

@Entity(name = "parkings")
@Component
@Getter
@Setter
public class ParkingEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer parkingNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Community community;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;



}