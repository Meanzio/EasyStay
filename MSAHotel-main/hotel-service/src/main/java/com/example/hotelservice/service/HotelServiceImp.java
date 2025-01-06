package com.example.hotelservice.service;

import com.example.hotelservice.entity.Hotel;
import com.example.hotelservice.entity.HotelOption;
import com.example.hotelservice.entity.HotelTitleImg;
import com.example.hotelservice.exception.HotelNotFoundException;
import com.example.hotelservice.repository.HotelOptionRepository;
import com.example.hotelservice.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HotelServiceImp implements HotelService {

    private final HotelRepository hotelRepository;
    private final HotelTitleImgService hotelTitleImgService;
    private final HotelOptionRepository hotelOptionRepository;

    @Autowired
    public HotelServiceImp(HotelRepository hotelRepository,
                           HotelTitleImgService hotelTitleImgService,
                           HotelOptionRepository hotelOptionRepository) {
        this.hotelRepository = hotelRepository;
        this.hotelTitleImgService = hotelTitleImgService;
        this.hotelOptionRepository = hotelOptionRepository;
    }

    @Override
    public Hotel insertHotel(Hotel hotel, MultipartFile hotelTitleImage) throws IOException {
        if (hotelTitleImage != null && !hotelTitleImage.isEmpty()) {
            String storedFileName = hotelTitleImgService.saveFile(hotelTitleImage);

            HotelTitleImg titleImg = HotelTitleImg.builder()
                    .hotelTitleImgUrl(storedFileName)
                    .hotel(hotel)
                    .build();

            hotel.setTitleImg(titleImg);
        }

        hotel = hotelRepository.save(hotel);

        List<HotelOption> options = hotel.getOptions();
        if (options != null && !options.isEmpty()) {
            for (HotelOption option : options) {
                option.setHotel(hotel);
                hotelOptionRepository.save(option);
            }
        }

        return hotel;
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public void updateHotel(Long hotelId, Hotel newHotel) {
        Optional<Hotel> existingHotel = hotelRepository.findById(hotelId);

        if (existingHotel.isPresent()) {
            Hotel updatedHotel = existingHotel.get();
            updatedHotel.setName(newHotel.getName());
            updatedHotel.setLocation(newHotel.getLocation());
            updatedHotel.setDescription(newHotel.getDescription());
            updatedHotel.setPricePerNight(newHotel.getPricePerNight());
            updatedHotel.setTitleImg(newHotel.getTitleImg());
            updatedHotel.setOptions(newHotel.getOptions());

            hotelRepository.save(updatedHotel);
        } else {
            throw new HotelNotFoundException("호텔 ID " + hotelId + " 를 못찾았습니다.");
        }
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public List<Hotel> getHotelByName(String name) {
        return hotelRepository.findByName(name);
    }

    @Override
    public List<Hotel> getHotelByLocal(String local) {
        return hotelRepository.findByLocation(local);
    }

    @Override
    public List<Hotel> getHotelByOption(String optionName) {
        List<HotelOption> options = hotelOptionRepository.findByOptionName(optionName);

        return options.stream()
                .map(HotelOption::getHotel)
                .distinct()
                .toList();
    }

    public List<Hotel> searchHotels(String name, String local, String startDate, String endDate, int numGuests) {
        if (name != null && !name.isEmpty()) {
            return hotelRepository.findByNameAndLocation(name, local);
        } else if (local != null && !local.isEmpty()) {
            return hotelRepository.findByLocation(local);
        } else {
            return hotelRepository.findAll();
        }
    }
}
