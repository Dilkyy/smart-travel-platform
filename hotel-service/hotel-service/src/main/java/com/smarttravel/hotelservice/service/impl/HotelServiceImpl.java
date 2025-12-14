package com.smarttravel.hotelservice.service.impl;

import com.smarttravel.hotelservice.dto.HotelDTO;
import com.smarttravel.hotelservice.entity.Hotel;
import com.smarttravel.hotelservice.exception.ResourceNotFoundException;
import com.smarttravel.hotelservice.repository.HotelRepository;
import com.smarttravel.hotelservice.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with id: " + id));
        return mapToDTO(hotel);
    }

    @Override
    public HotelDTO createHotel(HotelDTO dto) {
        Hotel hotel = Hotel.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .pricePerNight(dto.getPricePerNight())
                .availableRooms(dto.getAvailableRooms())
                .build();
        Hotel saved = hotelRepository.save(hotel);
        return mapToDTO(saved);
    }

    private HotelDTO mapToDTO(Hotel hotel) {
        return HotelDTO.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .location(hotel.getLocation())
                .pricePerNight(hotel.getPricePerNight())
                .availableRooms(hotel.getAvailableRooms())
                .build();
    }
}

