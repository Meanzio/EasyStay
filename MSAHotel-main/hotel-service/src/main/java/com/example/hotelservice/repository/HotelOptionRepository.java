package com.example.hotelservice.repository;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.entity.HotelOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelOptionRepository extends JpaRepository<HotelOption, Long> {
    List<HotelOption> findByHotelId(Hotel hotel);
    List<HotelOption> findByOptionName(String optionName);

}
