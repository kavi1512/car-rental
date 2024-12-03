package com.example.wheelwise.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.wheelwise.Model.Booking;
import com.example.wheelwise.Model.Vehicle;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByVehicle(Vehicle vehicle); // Fetch bookings for a specific vehicle
}
