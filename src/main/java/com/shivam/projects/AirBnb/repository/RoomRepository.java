package com.shivam.projects.AirBnb.repository;

import com.shivam.projects.AirBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
