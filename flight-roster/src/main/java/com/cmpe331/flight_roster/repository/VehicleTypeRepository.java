package com.cmpe331.flight_roster.repository;

import com.cmpe331.flight_roster.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTypeRepository extends JpaRepository<VehicleType, Integer> {
}