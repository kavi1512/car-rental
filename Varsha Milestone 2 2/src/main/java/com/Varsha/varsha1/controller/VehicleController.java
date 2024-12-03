package com.Varsha.varsha1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Varsha.varsha1.model.Vehicle;
import com.Varsha.varsha1.service.VehicleService;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        return ResponseEntity.ok(vehicles);
    }

    @GetMapping("/by-id")
    public ResponseEntity<Vehicle> getVehicleById(@RequestParam Long id) {
        Vehicle vehicle = vehicleService.getVehicleById(id);
        return vehicle != null ? ResponseEntity.ok(vehicle) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-type")
    public ResponseEntity<List<Vehicle>> getVehiclesByType(@RequestParam String type) {
        List<Vehicle> vehicles = vehicleService.getVehiclesByType(type);
        return ResponseEntity.ok(vehicles);
    }
    
    // API for brand
    @GetMapping("/by-brand")
    public ResponseEntity<List<Vehicle>> getVehiclesByCompanyName(@RequestParam String companyName) {
    List<Vehicle> vehicles = vehicleService.getVehiclesByCompanyName(companyName);
    return ResponseEntity.ok(vehicles);
    }


    // API for fuel types
    @GetMapping("/by-fuel")
public ResponseEntity<List<Vehicle>> getVehiclesByFuelType(@RequestParam String fuelType) {
    List<Vehicle> vehicles = vehicleService.getVehiclesByFuelType(fuelType);
    return ResponseEntity.ok(vehicles);
}

    // API for  transmission types
    @GetMapping("/by-transmission")
public ResponseEntity<List<Vehicle>> getVehiclesByTransmissionType(@RequestParam String transmissionType) {
    List<Vehicle> vehicles = vehicleService.getVehiclesByTransmissionType(transmissionType);
    return ResponseEntity.ok(vehicles);
}

}