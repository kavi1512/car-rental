package com.Varsha.varsha1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Varsha.varsha1.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    
    // Custom query method to find vehicles by company name
    List<Vehicle> findByCompanyName(String companyName);

    // Custom query method to find vehicles by fuel type
    List<Vehicle> findByFuelType(String fuelType);

    // Custom query method to find vehicles by transmission type
    List<Vehicle> findByTransmissionType(String transmissionType);
}
