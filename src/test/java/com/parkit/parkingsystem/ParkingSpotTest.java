package com.parkit.parkingsystem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.parkit.parkingsystem.constants.ParkingType;
import com.parkit.parkingsystem.dao.ParkingSpotDAO;
import com.parkit.parkingsystem.model.ParkingSpot;

class ParkingSpotTest {
	
	// check that the place numbers are not identical
	@Test
	void testNotEqual() {
		ParkingSpot p1 = new ParkingSpot(1, ParkingType.CAR, false);
		ParkingSpot p2 = new ParkingSpot(2, ParkingType.CAR, false);
		ParkingSpotDAO ps = new ParkingSpotDAO();
		ps.getNextAvailableSlot(ParkingType.CAR);
		
		assertFalse(p1.equals(p2));
	}
	
	// check that the place numbers are identical
	@Test
	void testEqual() {
		ParkingSpot p1 = new ParkingSpot(1, ParkingType.CAR, false);
		ParkingSpot p2 = new ParkingSpot(1, ParkingType.CAR, false);
		assertTrue(p1.equals(p2));
	}
	
	// check that the place number is not null
	@Test
	void testNull() {
		ParkingSpot p1 = new ParkingSpot(1, ParkingType.CAR, false);
		assertFalse(p1.equals(null));
	}
}
