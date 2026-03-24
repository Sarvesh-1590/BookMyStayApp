# BookMyStayApp - Hotel Booking Management System

This project demonstrates the design and implementation of a Hotel Booking Management System using Core Java and fundamental data structures. The system is developed incrementally, with each use case introducing key software engineering concepts.

## Project Structure

The project is organized by use cases, with each major feature implemented in its own branch and demonstrated via a dedicated entry point class.

### Implemented Use Cases

#### [Use Case 1: Application Entry & Welcome Message](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-1-application-entry-welcome-message)
- **Goal**: Establish the application entry point and basic console output.
- **Key Concepts**: `main()` method, `static` keyword, System output, JavaDoc.
- **Entry Point**: `UseCase1HotelBookingApp.java`

#### [Use Case 2: Basic Room Types & Static Availability](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-2-basic-room-types-static-availability)
- **Goal**: Introduce object modeling through inheritance and abstraction.
- **Key Concepts**: Abstract classes, Inheritance, Polymorphism, Encapsulation.
- **Entry Point**: `UseCase2HotelBookingApp.java`

#### [Use Case 3: Centralized Room Inventory Management](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-3-centralized-room-inventory-management)
- **Goal**: Centralize system state using a consistent data structure.
- **Key Concepts**: `HashMap` for state management, Single Source of Truth, O(1) Lookup.
- **Entry Point**: `UseCase3InventorySetup.java`

#### [Use Case 4: Room Search & Availability Check](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-4-room-search-availability-check)
- **Goal**: Enable safe, read-only search functionality.
- **Key Concepts**: Read-only access, Defensive programming, Separation of concerns.
- **Entry Point**: `UseCase4RoomSearch.java`

#### [Use Case 5: Booking Request (First-Come-First-Served)](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-5-booking-request-fifo)
- **Goal**: Handle multiple requests fairly using arrival order.
- **Key Concepts**: `Queue` data structure, FIFO principle, Decoupling intake from allocation.
- **Entry Point**: `UseCase5BookingRequestQueue.java`

#### [Use Case 6: Reservation Confirmation & Room Allocation](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-6-reservation-confirmation-room-allocation)
- **Goal**: Confirm booking requests by assigning unique room IDs while ensuring inventory consistency.
- **Key Concepts**: `Set` for uniqueness, `Map` for tracking, Atomic logical operations, Double-booking prevention.
- **Entry Point**: `UseCase6RoomAllocation.java`

#### [Use Case 7: Add-On Service Selection](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-7-addon-service-selection)
- **Goal**: Extend the booking model to support optional services like breakfast or spa.
- **Key Concepts**: One-to-Many Relationship, Business Extensibility, Composition over Inheritance, Cost Aggregation.
- **Entry Point**: `UseCase7AddOnServiceSelection.java`

#### [Use Case 8: Booking History & Reporting](https://github.com/Sarvesh-1590/BookMyStayApp/tree/use-case-8-booking-history-reporting)
- **Goal**: Introduce historical tracking of confirmed bookings for audits and operational visibility.
- **Key Concepts**: List Data Structure, Ordered Storage, Operational Visibility, Persistence Mindset.
- **Entry Point**: `UseCase8BookingHistory.java`

---

## How to Run

1. Compile the Java files:
   ```bash
   javac *.java
   ```
2. Run a specific use case entry point:
   ```bash
   java UseCase8BookingHistory
   ```
