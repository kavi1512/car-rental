
package com.example.wheelwise.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wheelwise.Model.Vehicle;
import com.example.wheelwise.repo.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
    public List<Vehicle> findAvailableVehiclesWithFilters(
            String location,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String type,
            String companyName,
            String fuelType,
            String transmissionType,
            Integer numofseats,
            Double minPrice,
            Double maxPrice
    ) {
        // Start by retrieving all available vehicles
        List<Vehicle> vehicles = vehicleRepository.findAllAvailableVehicles();

        // Apply filters based on selected parameters
        return vehicles.stream()
                .filter(vehicle -> (location == null || location.isEmpty() || vehicle.getLocation().equalsIgnoreCase(location)))
                .filter(vehicle -> (type == null || type.isEmpty() || vehicle.getType().equalsIgnoreCase(type)))
                .filter(vehicle -> (companyName == null || companyName.isEmpty() || vehicle.getCompanyName().equalsIgnoreCase(companyName)))
                .filter(vehicle -> (fuelType == null || fuelType.isEmpty() || vehicle.getFuelType().equalsIgnoreCase(fuelType)))
                .filter(vehicle -> (transmissionType == null || transmissionType.isEmpty() || vehicle.getTransmissionType().equalsIgnoreCase(transmissionType)))
                .filter(vehicle -> (numofseats == null || vehicle.getCapacity() == numofseats))
                .filter(vehicle -> (startTime == null || endTime == null || isVehicleAvailable(vehicle, startTime, endTime)))
                .filter(vehicle -> (minPrice == null || isPriceInRange(vehicle.getPricePerDay(), minPrice, true)))  // Filter by min price
                .filter(vehicle -> (maxPrice == null || isPriceInRange(vehicle.getPricePerDay(), maxPrice, false)))  // Filter by max price
                .collect(Collectors.toList());
    }

    private boolean isPriceInRange(BigDecimal pricePerDay, Double price, boolean isMinPrice) {
        if (price == null) {
            return true; // No price filter applied
        }
        
        // Convert Double to BigDecimal for comparison
        BigDecimal priceToCompare = BigDecimal.valueOf(price);
        
        if (isMinPrice) {
            // Check if pricePerDay >= minPrice
            return pricePerDay.compareTo(priceToCompare) >= 0;
        } else {
            // Check if pricePerDay <= maxPrice
            return pricePerDay.compareTo(priceToCompare) <= 0;
        }
    }
    private boolean isVehicleAvailable(Vehicle vehicle, LocalDateTime startTime, LocalDateTime endTime) {
        // Implement the availability logic to check if the vehicle is available between startTime and endTime
        return true; // Placeholder for actual availability logic
    }
    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}

