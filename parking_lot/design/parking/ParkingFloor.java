package parking_lot.design.parking;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import parking_lot.design.vehicle.Vehicle;

public class ParkingFloor {

    private final String name;
    private final Map<ParkingSpotType, List<ParkingSpot>> spotsByType = new EnumMap<>(ParkingSpotType.class);

    public ParkingFloor(String name) {
        this.name = name;
        for (ParkingSpotType type : ParkingSpotType.values()) {
            spotsByType.put(type, new ArrayList<>());
        }
    }

    public String getName() {
        return name;
    }

    public void addSpot(ParkingSpot spot) {
        spotsByType.get(spot.getType()).add(spot);
    }

    public Optional<ParkingSpot> findSpot(Vehicle vehicle) {
        for (ParkingSpotType type : ParkingSpotType.values()) {
            for (ParkingSpot spot : spotsByType.get(type)) {
                if (spot.isAvailable() && spot.canFitVehicle(vehicle)) {
                    return Optional.of(spot);
                }
            }
        }
        return Optional.empty();
    }

    public void vacateSpot(String spotId) {
        for (List<ParkingSpot> spots : spotsByType.values()) {
            for (ParkingSpot spot : spots) {
                if (spot.getId().equals(spotId)) {
                    spot.removeVehicle();
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Unknown spot id: " + spotId);
    }

    public int availableSpots(ParkingSpotType type) {
        int count = 0;
        for (ParkingSpot spot : spotsByType.get(type)) {
            if (spot.isAvailable()) {
                count++;
            }
        }
        return count;
    }
}
