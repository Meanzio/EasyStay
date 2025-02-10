package com.example.hotelservice.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelDateService implements HotelDate {

    private List<LocalDate> availableDates = new ArrayList<>();
    private List<LocalDate> reservedDates = new ArrayList<>();

    @Override
    public List<LocalDate> getAvailableDates() {
        List<LocalDate> available = new ArrayList<>(availableDates);
        available.removeAll(reservedDates);
        return available;
    }

    @Override
    public boolean isAvailableOn(LocalDate date) {
        return availableDates.contains(date) && !reservedDates.contains(date);
    }

    @Override
    public void addReservation(LocalDate date) {
        if (isAvailableOn(date)) {
            reservedDates.add(date);
        } else {
            throw new IllegalArgumentException("The date " + date + " is not available.");
        }
    }

    @Override
    public void cancelReservation(LocalDate date) {
        if (reservedDates.contains(date)) {
            reservedDates.remove(date);
        } else {
            throw new IllegalArgumentException("No reservation found for the date " + date);
        }
    }

    // 예약 가능한 날짜 추가
    public void addAvailableDate(LocalDate date) {
        availableDates.add(date);
    }
}
