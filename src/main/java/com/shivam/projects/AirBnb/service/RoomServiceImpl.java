package com.shivam.projects.AirBnb.service;

import com.shivam.projects.AirBnb.dto.RoomDto;
import com.shivam.projects.AirBnb.entity.Hotel;
import com.shivam.projects.AirBnb.entity.Room;
import com.shivam.projects.AirBnb.exception.ResourceNotFoundException;
import com.shivam.projects.AirBnb.repository.HotelRepository;
import com.shivam.projects.AirBnb.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDto createNewRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room in hotel with ID : {}", hotelId);
        Hotel hotel= hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        Room room=modelMapper.map(roomDto,Room.class);
        room.setHotel(hotel);
        room= roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("Getting all the rooms in hotel with ID : {}", hotelId);
        Hotel hotel= hotelRepository
                .findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the room with ID : {}", roomId);
        Room room= roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with ID: "+roomId));
        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with ID: {}", roomId);
        Room room= roomRepository
                .findById(roomId)
                .orElseThrow(()-> new ResourceNotFoundException("Room not found with ID: "+roomId));

        inventoryService.deleteFutureInventories(room);
        roomRepository.deleteById(roomId);
    }
}
