package com.example.hotelservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "hotel_contents")
public class HotelContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column(name = "hotel_contents_img_org")
    private String hotelContentsImgOrg;

    @Column(name = "hotel_contents_img_url")
    private String hotelContentsImgUrl;

    @Column(name = "hotel_contents_text", columnDefinition = "TEXT")
    private String hotelContentsText;
}
