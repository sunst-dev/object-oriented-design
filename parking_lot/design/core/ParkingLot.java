package parking_lot.design.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import parking_lot.design.assignment.SpotAssignmentStrategy;
import parking_lot.design.parking.ParkingFloor;
import parking_lot.design.parking.ParkingSpot;
import parking_lot.design.parking.ParkingSpotType;
import parking_lot.design.pricing.PricingStrategy;
import parking_lot.design.ticket.Ticket;
import parking_lot.design.vehicle.Vehicle;

public class ParkingLot {

    private final List<ParkingFloor> floors = new ArrayList<>();
    private final Map<String, Ticket> activeTickets = new HashMap<>();
    private final SpotAssignmentStrategy spotAssignmentStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingLot(SpotAssignmentStrategy spotAssignmentStrategy, PricingStrategy pricingStrategy) {
        this.spotAssignmentStrategy = spotAssignmentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void addFloor(ParkingFloor floor) {
        floors.add(floor);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> spot = spotAssignmentStrategy.findSpot(vehicle, floors);
        if (spot.isEmpty()) {
            throw new IllegalStateException("No available spot for vehicle type " + vehicle.getType());
        }
        ParkingSpot assignedSpot = spot.get();
        assignedSpot.assignVehicle(vehicle);
        Ticket ticket = new Ticket(vehicle, assignedSpot);
        vehicle.assignTicket(ticket);
        activeTickets.put(ticket.getId(), ticket);
        return ticket;
    }

    public BigDecimal exitVehicle(String ticketId) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Unknown ticket id: " + ticketId);
        }
        BigDecimal amount = pricingStrategy.calculateFee(ticket);
        ticket.getSpot().removeVehicle();
        ticket.close(amount);
        return amount;
    }

    public int availableSpots(ParkingSpotType type) {
        int total = 0;
        for (ParkingFloor floor : floors) {
            total += floor.availableSpots(type);
        }
        return total;
    }

    public int activeTicketCount() {
        return activeTickets.size();
    }
}
