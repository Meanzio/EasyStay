package com.example.hotelservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hotel_title_imgs")
public class HotelTitleImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "hotel_title_img_org", nullable = false)
    private String hotelTitleImgOrg;

    @Column(name = "hotel_title_img_url", nullable = false)
    private String hotelTitleImgUrl;


}
