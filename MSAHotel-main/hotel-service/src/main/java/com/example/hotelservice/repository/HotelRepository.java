package com.example.hotelservice.repository;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.entity.HotelOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByName(String name);
    List<Hotel> findByLocation(String local);
    List<Hotel> findByOptionName(String option);
    List<HotelOption> findByHotelId(Long hotelId);


}
