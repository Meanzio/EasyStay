package com.example.hotelservice.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 100)
    private String location;

    @Column(length = 100)
    private String managerName;

    @Column(length = 100)
    private String managerPhone;

    @Column
    private double rating; // 평점 필드 (0에서 5 사이의 값)

    @Column(length = 100)
    private String managerJob;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, columnDefinition = "DECIMAL(10,2)")
    private Double pricePerNight;

    // 추가된 필드
    @Column(name = "hotel_service", length = 255)
    private String hotelService;

    @Column(name = "img_url", length = 255)
    private String imgUrl;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelAvailableDate> availableDates;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelContent> contents;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelOption> options;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private HotelTitleImg titleImg;


    public double calculateAverageRating(List<Double> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }
        return ratings.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }
}
