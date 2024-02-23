package com.parking.parkingmanagement.controller;

import com.parking.parkingmanagement.mapper.parking.ParkingMapper;
import com.parking.parkingmanagement.model.parking.ParkingCreateRequest;
import com.parking.parkingmanagement.model.parking.ParkingDto;
import com.parking.parkingmanagement.model.parking.ParkingEntity;
import com.parking.parkingmanagement.service.parking.ParkingService;
import com.parking.parkingmanagement.service.user.UserService;
import com.parking.parkingmanagement.util.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/parking")
@RequiredArgsConstructor
public class ParkingController {


    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;


    @GetMapping
    public ResponseEntity<List<ParkingDto>> getParking() {
        return ResponseEntity.ok(parkingService.findAllByCommunity(CurrentUser.getCommunity()));
    }

    @GetMapping ("/available")
    public ResponseEntity<List<ParkingDto>> getAvailableParking() {
        return ResponseEntity.ok(parkingService.availableParkingByCommunity(CurrentUser.getCommunity()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDto> park(@PathVariable Integer id) {
        return ResponseEntity.ok(parkingService.park(id));
    }

    @PostMapping()
    public ResponseEntity<ParkingDto> create(@RequestBody ParkingCreateRequest parkingCreateRequest) {
        return ResponseEntity.ok(parkingService.create(parkingCreateRequest));
    }

    @PutMapping
    public ResponseEntity<ParkingDto> update(@RequestBody ParkingEntity parkingEntity) {
        return ResponseEntity.ok(parkingService.update(parkingEntity));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ParkingDto> delete(@PathVariable Integer id) {
        ParkingDto byRowAndCommunity = parkingService.findByRowAndCommunity(id, CurrentUser.getCommunity());
        parkingService.delete(parkingMapper.toEntity(byRowAndCommunity));
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/release")
    public ResponseEntity<ParkingDto> releaseParking() {
        parkingService.release();
        return ResponseEntity.ok().build();
    }






}
