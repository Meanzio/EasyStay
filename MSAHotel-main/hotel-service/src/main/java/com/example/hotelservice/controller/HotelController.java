package com.example.hotelservice.controller;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelServiceImp hotelService;

    // 호텔 등록
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
        try {
            Hotel newHotel = hotelService.insertHotel(hotel, null); // titleImage 파라미터는 나중에 추가로 처리할 수 있습니다.
            return ResponseEntity.ok(newHotel);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    // 전체 호텔 조회
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    // 이름으로 호텔 조회
    @GetMapping("/hotel/{name}")
    public ResponseEntity<List<Hotel>> getHotelByName(
            @PathVariable String name) {
        List<Hotel> hotels = hotelService.getHotelByName(name);
        return ResponseEntity.ok(hotels);
    }

    // 지역으로 호텔 조회
    @GetMapping("/local/{location}")
    public ResponseEntity<List<Hotel>> getHotelByLocation(
            @PathVariable String location) {
        List<Hotel> hotels = hotelService.getHotelByLocal(location);
        return ResponseEntity.ok(hotels);
    }

    // 옵션으로 호텔 조회
    @GetMapping("/service/{option}")
    public ResponseEntity<List<Hotel>> getHotelByService(
            @PathVariable String option) {
        List<Hotel> hotels = hotelService.getHotelByOption(option);
        return ResponseEntity.ok(hotels);
    }

    // 다양한 조건으로 호텔 조회 (이름, 지역, 날짜, 인원 등)
    @GetMapping("/search")
    public ResponseEntity<List<Hotel>> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String local,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) Integer numGuests) {
        List<Hotel> hotels = hotelService.searchHotels(name, local, startDate, endDate, numGuests);
        return ResponseEntity.ok(hotels);
    }
}
