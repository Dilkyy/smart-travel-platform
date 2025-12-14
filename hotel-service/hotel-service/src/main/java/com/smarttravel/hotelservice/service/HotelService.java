package com.smarttravel.hotelservice.service;

import com.smarttravel.hotelservice.dto.HotelDTO;

public interface HotelService {
    HotelDTO getHotelById(Long id);
    HotelDTO createHotel(HotelDTO dto);
}
