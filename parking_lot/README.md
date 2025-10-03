# Parking Lot Design

## Problem Overview
Design a flexible parking lot system that can track available spaces, support multiple vehicle types, and provide reliable entry/exit operations. The lot spans multiple levels (floors), each containing a mix of compact, large, and motorcycle spots. Drivers should be able to see whether a spot is available, enter, park, pay, and exit with minimal friction, while attendants need an overview of occupancy.

Key requirements:
- Handle cars, motorcycles, and large vehicles with different spot sizing rules.
- Track availability per floor and across the entire facility in real time.
- Support ticketing for entry/exit, including pricing based on parking duration.
- Provide administrative hooks for reserving spots and marking areas as closed for maintenance.

## Solution Summary
The design is split into focused packages:
- `core` supplies the `ParkingLot` orchestrator that ties together spot assignment, ticketing, and billing.
- `parking` contains floors and spot models responsible for capacity tracking and fit checks.
- `vehicle` models the `Vehicle` hierarchy so spot matching can remain type-aware.
- `ticket` owns ticket lifecycle, timestamps, and captured charges.
- `pricing` defines interchangeable fee strategies (flat vs. hourly) that operate on tickets.
- `assignment` provides strategies for selecting the next available spot across floors.

This modular layout keeps responsibilities isolated while making it straightforward to add new vehicle types, spot rules, or pricing policies without touching the rest of the system.
