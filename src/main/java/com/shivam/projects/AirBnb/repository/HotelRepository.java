package com.shivam.projects.AirBnb.repository;

import com.shivam.projects.AirBnb.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
