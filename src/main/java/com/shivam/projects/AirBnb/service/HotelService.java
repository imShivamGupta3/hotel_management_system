package com.shivam.projects.AirBnb.service;

import com.shivam.projects.AirBnb.dto.HotelDto;
import com.shivam.projects.AirBnb.entity.Hotel;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long hotelId);

}
