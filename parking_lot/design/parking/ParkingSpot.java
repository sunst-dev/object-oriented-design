package parking_lot.design.parking;

import parking_lot.design.vehicle.Vehicle;
import parking_lot.design.vehicle.VehicleType;

public class ParkingSpot {

    private final String id;
    private final ParkingSpotType type;
    private Vehicle currentVehicle;

    public ParkingSpot(String id, ParkingSpotType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public ParkingSpotType getType() {
        return type;
    }

    public boolean isAvailable() {
        return currentVehicle == null;
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            return false;
        }
        VehicleType vehicleType = vehicle.getType();
        return switch (vehicleType) {
            case MOTORCYCLE -> true;
            case CAR -> type == ParkingSpotType.COMPACT || type == ParkingSpotType.LARGE;
            case TRUCK -> type == ParkingSpotType.LARGE;
        };
    }

    public void assignVehicle(Vehicle vehicle) {
        if (!isAvailable()) {
            throw new IllegalStateException("Spot already occupied");
        }
        if (!canFitVehicle(vehicle)) {
            throw new IllegalArgumentException("Vehicle does not fit spot type");
        }
        this.currentVehicle = vehicle;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
    }

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
}
