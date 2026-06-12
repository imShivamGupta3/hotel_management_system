package com.shivam.projects.AirBnb.service;

import com.shivam.projects.AirBnb.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);
}
