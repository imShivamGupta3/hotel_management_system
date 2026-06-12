package com.shivam.projects.AirBnb.repository;

import com.shivam.projects.AirBnb.entity.Inventory;
import com.shivam.projects.AirBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
