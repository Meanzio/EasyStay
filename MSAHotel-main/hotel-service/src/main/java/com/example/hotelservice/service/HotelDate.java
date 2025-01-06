package com.example.hotelservice.service;

import java.time.LocalDate;
import java.util.List;

public interface HotelDate {

    // 숙박 가능한 날짜 리스트 반환
    List<LocalDate> getAvailableDates();

    // 특정 날짜가 예약 가능한지 여부 확인
    boolean isAvailableOn(LocalDate date);

    // 특정 날짜에 예약 추가
    void addReservation(LocalDate date);

    // 특정 날짜에 예약 취소
    void cancelReservation(LocalDate date);
}
