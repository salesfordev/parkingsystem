package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;
import java.util.concurrent.TimeUnit;

public class FareCalculatorService {

	public TicketDAO ticketDAO = new TicketDAO();

	public void calculateFare(Ticket ticket) {
		if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
			throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
		}

		long inHour = ticket.getInTime().getTime();
		long outHour = ticket.getOutTime().getTime(); // getTime() in millisecondes

		long duration = outHour - inHour;
		float durationInMinutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS); // Duration in minutes
		if (durationInMinutes <= 30) {
			ticket.setPrice(0);
		}

		else {
			switch (ticket.getParkingSpot().getParkingType()) {
			case CAR: {
				ticket.setPrice(durationInMinutes * Fare.CAR_RATE_PER_HOUR / 60);
				break;
			}
			case BIKE: {
				ticket.setPrice(durationInMinutes * Fare.BIKE_RATE_PER_HOUR / 60);
				break;
			}

			default:
				throw new IllegalArgumentException("Unkown Parking Type");

			}

			Ticket existingTicket = ticketDAO.getTicket(ticket.getVehicleRegNumber());
			if (existingTicket != null) {
				double reducePrice = ticket.getPrice() - (ticket.getPrice() * 0.05);
				ticket.setPrice(reducePrice);
			}
		}
	}
}