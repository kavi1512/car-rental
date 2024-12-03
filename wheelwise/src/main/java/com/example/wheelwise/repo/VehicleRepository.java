package com.example.wheelwise.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.wheelwise.Model.Vehicle;
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	@Query("SELECT v FROM Vehicle v WHERE v.id NOT IN (SELECT b.vehicle.id FROM Booking b WHERE b.status = 'CONFIRMED')")
	List<Vehicle> findAllAvailableVehicles();
	
}
