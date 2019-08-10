package com.revision.ctci.gobjectorienteddesign;

import java.util.ArrayList;
import java.util.LinkedList;

public class DParkingLot {
    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(new int[]{5, 8, 4});
        parkingLot.showAvailablePlaces();
    }
}

class ParkingLot {
    private final String[] type = new String[]{"SMALL", "MEDIUM", "LARGE"};

    private ArrayList<ArrayList<Size>> lot;//Currently row denotes the Small | Medium | Large
    private ArrayList<LinkedList<Integer>> availableSpaces;
    /* ArrayList for the Small | Medium | Large which will always remain at three
     * LinkedList for the available queue which will be frequently removed and added
     * so there is a need to do that action in constant time BigO(1) or approximately
     * nearby and LinkedList will do the trick */

    /* The assumption here is that the vehicle are of various sizes and the lot spaces
     * Small | Medium | Large will accommodate all these types and that free spaces are
     * accepted since it will not be easy  if we look for compact spaces for them if will
     * be difficult to take the vehicle out when it becomes surrounded */

    ParkingLot(int[] sizes) {
        initializeLot(sizes);
        initializeAvailable(sizes);
    }

    private void initializeLot(int[] sizes) {
        //Initializing using the length because the building space will not vary also this will allow people to
        // resize when required and reallocate the spaces accordingly [scalability decision]
        this.lot = new ArrayList<>(sizes.length);
        for (int i : sizes) {
            this.lot.add(new ArrayList<>(i));
        }
    }

    private void initializeAvailable(int[] locations) {
        this.availableSpaces = new ArrayList<>(locations.length);
        for (int i = 0; i < locations.length; i++) {
            this.availableSpaces.add(new LinkedList<>());
            for (int j = 0; j < locations[ i ]; j++) {
                this.availableSpaces.get(i).add(j);
            }
        }
    }

    void showAvailablePlaces() {
        for (int i = 0; i < this.availableSpaces.size(); i++) {
            System.out.print(type[ i ] + ": ");
            for (Integer location : this.availableSpaces.get(i)) {
                System.out.print(location + " ");
            }
            System.out.println();
        }
    }

    public boolean allocatePlace(Vehicle vehicle) {
        /* DECIDE THE VEHICLE TYPE AND GO TO THE CORRESPONDING LOCATION IN THE
         * AVAILABLE LIST AND CHECK FOR THE SIZE OF THE LIST
         * - IF INDEX IS AVAILABLE THEN REMOVE THE INDEX FROM AVAILABLE LIST
         *   AFTER ALLOCATING THE VEHICLE IN THE LOCATION IN THE LOT ARRAY LIST */
        return true;
    }

    public boolean freeUpSpace(Vehicle vehicle) {
        /* HERE THE FINDING THE VEHICLE WILL TAKE LONGER DURATION. IF THERE A IS NEED
         * TO OPTIMIZE THEN TRY USING ASSOCIATE ARRAY (HASH MAP STRATEGY) TO FIND
         * THE VEHICLE IN CONSTANT TIME*/
        return true;
    }

    public ArrayList<ArrayList<Size>> getLot() {
        return lot;
    }

    public ArrayList<LinkedList<Integer>> getAvailableSpaces() {
        return availableSpaces;
    }
}

class Size {
    private int[] dimension;

    Size(int[] dimension) {
        this.dimension = dimension;
    }

    public int[] getDimension() {
        return dimension;
    }

    /* Logic to determine a specific place */
    private boolean canFit(int[] dimension) {
        return false;
    }
}

class Small extends Size {
    public Small(int[] dimension) {
        super(dimension);
    }
}

class Medium extends Size {
    public Medium(int[] dimension) {
        super(dimension);
    }
}

class Large extends Size {
    public Large(int[] dimension) {
        super(dimension);
    }
}

/* Classification of vehicles based on the space occupied */
enum Type {
    TWO_WHEELER(6, 2, 4), FOUR_WHEELER_SMALL(16, 10, 8), FOUR_WHEELER_MEDIUM(20, 16, 12), FOUR_WHEELER_LARGE(20, 16, 16), TRUCK(40, 16, 24), LORRY(60, 20, 32), BUS(80, 20, 32);

    private int length;
    private int breadth;
    private int height;

    Type(int length, int breadth, int height) {
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public int getBreadth() {
        return breadth;
    }

    public int getHeight() {
        return height;
    }
}

class Vehicle {
    //Dimension - Length, Breadth and Height [height might be useful to determine if we could know the height of the building as well]
    //Size -
    Type type;
    int[] dimension;
    String numberPlate;
    Engine engine;
    Fuel fuel;//Fuel means it can be petrol | diesel | electric
    double capacity;//It will be in percentage whatever the type of fuel
    int seats;//No of seats available

    public Vehicle(Type type) {
        this.dimension = getDimension(type);
    }

    private int[] getDimension(Type type) {
        int[] dimension = new int[ 3 ];
        dimension[ 0 ] = type.getLength();
        dimension[ 1 ] = type.getBreadth();
        dimension[ 2 ] = type.getHeight();
        return dimension;
    }
}

/* ENGINE related classes START */
class Engine {
    private String model;
    private Transmission transmission;
    private Power power;
    private Torque torque;

    public Engine(String model, Transmission transmission, Power power, Torque torque) {
        this.model = model;
        this.transmission = transmission;
        this.power = power;
        this.torque = torque;
    }

    public String getModel() {
        return model;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public Power getPower() {
        return power;
    }

    public Torque getTorque() {
        return torque;
    }
}

class Transmission {
    private String value;

    public Transmission(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class Power {
    private int horsePower;

    public Power(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getHorsePower() {
        return horsePower;
    }
}

class Torque {
    private int value;

    public Torque(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
/* ENGINE related classes END */

class Fuel {
    static final String PETROL = "Petrol";
    static final String DIESEL = "Diesel";
    static final String ELECTRIC = "Electric";
    private String type;

    public Fuel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

class TwoWheeler extends Vehicle {
    public TwoWheeler(Type type) {
        super(type);
    }
}

class FourWheeler extends Vehicle {
    AudioPlayer audioPlayer;
    AC airConditioner;
    AirBags airBags;
    boolean convertible;
    Body body;

    public FourWheeler(Type type) {
        super(type);
    }

    /* TO BE IMPLEMENTED: CAN BE MOVED OUTSIDE */
    private class AudioPlayer {

    }

    private class AC {

    }

    private class AirBags {

    }

    private class Body {

    }
}

class Truck extends Vehicle {
    private Container container;

    public Truck(Type type, Container container) {
        super(type);
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }
}

class Lorry extends Vehicle {
    private Container container;

    public Lorry(Type type, Container container) {
        super(type);
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }
}

class Bus extends Vehicle {
    public Bus(Type type) {
        super(type);
    }
}

class Container {
    private int capacity;

    public Container(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}