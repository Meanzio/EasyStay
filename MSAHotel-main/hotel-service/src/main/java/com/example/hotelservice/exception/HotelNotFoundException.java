package com.example.hotelservice.exception;

public class HotelNotFoundException extends RuntimeException {

    // 기본 생성자
    public HotelNotFoundException(String message) {
        super(message);
    }

    // 원인(exception)도 함께 전달할 수 있는 생성자
    public HotelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
