package com.example.hotelservice.service;

import com.example.hotelservice.entity.Hotel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface HotelService {

    // 호텔 등록
    public Hotel insertHotel(Hotel hotel, MultipartFile hotelTitleImage) throws IOException;
    // 호텔 삭제
    void deleteHotel(Long id);
    // 호텔 수정
    void updateHotel(Long hotelId, Hotel hotel);

    // 호텔 전체 조회
    List<Hotel> getAllHotels();
    // 호텔 Name 검색
    List<Hotel> getHotelByName(String name);
    // 호텔 Local 검색
    List<Hotel> getHotelByLocal(String local);
    // 호텔 Option 검색
    List<Hotel> getHotelByOption(String option);



}
