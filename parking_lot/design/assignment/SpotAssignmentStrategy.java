package parking_lot.design.assignment;

import java.util.List;
import java.util.Optional;

import parking_lot.design.parking.ParkingFloor;
import parking_lot.design.parking.ParkingSpot;
import parking_lot.design.vehicle.Vehicle;

public interface SpotAssignmentStrategy {
    Optional<ParkingSpot> findSpot(Vehicle vehicle, List<ParkingFloor> floors);
}
