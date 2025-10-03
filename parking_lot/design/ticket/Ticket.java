package parking_lot.design.ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import parking_lot.design.parking.ParkingSpot;
import parking_lot.design.vehicle.Vehicle;

public class Ticket {

    private final String id;
    private final Vehicle vehicle;
    private final ParkingSpot spot;
    private final LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private TicketStatus status = TicketStatus.ACTIVE;
    private BigDecimal amountCharged = BigDecimal.ZERO;

    public Ticket(Vehicle vehicle, ParkingSpot spot) {
        this.id = UUID.randomUUID().toString();
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public BigDecimal getAmountCharged() {
        return amountCharged;
    }

    public Duration getDuration() {
        LocalDateTime end = exitTime != null ? exitTime : LocalDateTime.now();
        return Duration.between(entryTime, end);
    }

    public void close(BigDecimal amount) {
        this.exitTime = LocalDateTime.now();
        this.amountCharged = amount;
        this.status = TicketStatus.CLOSED;
    }
}
