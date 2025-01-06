package com.example.hotelservice.controller;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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
//        Hotel newHotel = hotelService.insertHotel(hotel);
        return null;
    }


    // 전체 호텔 조회
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }


    // 이름으로 호텔 조회
    @GetMapping("/hotel/{name}")
    public ResponseEntity<Hotel> getHotelByName(
            @RequestParam Long id,
            @PathVariable String name,
            Model model
    ) {
        return null;
    }

    // 지역으로 호텔 조회
    @GetMapping("/local/{location}")
    public ResponseEntity<Hotel> getHotelByLocal(
            @PathVariable String Location,
            @RequestParam Long id,
            Model model) {
        return null;
    }

    // 상황으로 호텔 조회
    @GetMapping("/local/{hotelService}")
    public ResponseEntity<Hotel> getHotelBySerivce(
            @PathVariable String hotelService,
            @RequestParam Long id,
            Model model) {
        return null;
    }

}
