package parking_lot.design.vehicle;

import parking_lot.design.ticket.Ticket;

public abstract class Vehicle {

    private final VehicleType type;
    private final String licensePlate;
    private Ticket ticket;

    protected Vehicle(VehicleType type, String licensePlate) {
        this.type = type;
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void assignTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}
