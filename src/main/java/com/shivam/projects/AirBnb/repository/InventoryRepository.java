package com.shivam.projects.AirBnb.repository;

import com.shivam.projects.AirBnb.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
