package parking_lot.design.assignment;

import java.util.List;
import java.util.Optional;

import parking_lot.design.parking.ParkingFloor;
import parking_lot.design.parking.ParkingSpot;
import parking_lot.design.vehicle.Vehicle;

public class SimpleSpotAssignmentStrategy implements SpotAssignmentStrategy {

    @Override
    public Optional<ParkingSpot> findSpot(Vehicle vehicle, List<ParkingFloor> floors) {
        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spot = floor.findSpot(vehicle);
            if (spot.isPresent()) {
                return spot;
            }
        }
        return Optional.empty();
    }
}
