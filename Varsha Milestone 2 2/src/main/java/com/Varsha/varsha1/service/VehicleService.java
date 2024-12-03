package com.Varsha.varsha1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Varsha.varsha1.model.Vehicle;
import com.Varsha.varsha1.repository.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // Existing method to fetch all vehicles
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    // Existing method to fetch a vehicle by ID
    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }


    // Existing method to get vehicles by type
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> vehicles = getAllVehicles();
        return vehicles.stream()
                       .filter(vehicle -> vehicle.getType().equalsIgnoreCase(type))
                       .collect(Collectors.toList());
    }

    // New method to get vehicles by company name
    public List<Vehicle> getVehiclesByCompanyName(String companyName) {
        return vehicleRepository.findByCompanyName(companyName);
    }

    // New method to get vehicles by fuel type
    public List<Vehicle> getVehiclesByFuelType(String fuelType) {
        List<Vehicle> vehicles = getAllVehicles();
        return vehicles.stream()
                       .filter(vehicle -> vehicle.getFuelType().equalsIgnoreCase(fuelType))
                       .collect(Collectors.toList());
    }

    // New method to get vehicles by transmission type
    public List<Vehicle> getVehiclesByTransmissionType(String transmissionType) {
        List<Vehicle> vehicles = getAllVehicles();
        return vehicles.stream()
                       .filter(vehicle -> vehicle.getTransmissionType().equalsIgnoreCase(transmissionType))
                       .collect(Collectors.toList());
    }

    // Method to update the rating of a vehicle
    public void updateVehicleRating(long vehicleId, double rating) {
        // Fetch the vehicle by ID
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElse(null);
        
        if (vehicle != null) {
            // Set the new rating
            vehicle.setRating(rating);
            
            // Save the updated vehicle back to the database
            vehicleRepository.save(vehicle);
        }
    }
}
