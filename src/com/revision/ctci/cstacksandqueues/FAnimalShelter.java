package com.revision.ctci.cstacksandqueues;

import java.util.LinkedList;

public class FAnimalShelter {
    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        shelter.enqueue(new Dog(1566246143042L, "Danny"));
        shelter.enqueue(new Cat(1566246143342L, "Felix"));
        shelter.enqueue(new Cat(1566248143042L, "Jonas"));
        shelter.enqueue(new Dog(1566249143042L, "Roger"));
        shelter.enqueue(new Cat(1566256143042L, "Skyle"));
        shelter.enqueue(new Dog(1566256143040L, "Jhonny"));
        shelter.enqueue(new Dog(1566266143142L, "Tommy"));
        shelter.enqueue(new Cat(1566276143142L, "Shelly"));
        shelter.enqueue(new Cat(1566296143142L, "Cory"));
        shelter.enqueue(new Dog(1568266143142L, "Ranger"));
        shelter.enqueue(new Cat(1576266143142L, "Joan"));
        shelter.enqueue(new Cat(1596266143142L, "Xenith"));
        shelter.enqueue(new Dog(1610266143142L, "Kenny"));

        shelter.show();
        print(shelter.dequeueAny(), "Deque Any: ");
        print(shelter.dequeueCat(), "Deque Cat: ");
        print(shelter.dequeueCat(), "Deque Cat: ");
        print(shelter.dequeueDog(), "Deque Dog: ");
        print(shelter.dequeueAny(), "Deque Any: ");
        print(shelter.dequeueAny(), "Deque Any: ");
        print(shelter.dequeueCat(), "Deque Cat: ");
        shelter.show();
    }

    private static void print(Animal animal, String message) {
        System.out.println(message + (animal != null ? animal.getName() : null));
    }
}

class Shelter {
    /* If space is a concern the we can keep the Cats and Dogs in the same queue and
     * work it out. Insertion will be like normal queue(If some animal comes it goes into
     * the last of the queue). When the person comes to get he has three options
     * dequeueAny, dequeueDog, dequeueCat
     * only when dog or cat are specified separately we could use local queue to poll until
     * a specified type is obtained then remove that and continue addition in the queue and
     * once that is complete again put it back in order */
    LinkedList<Animal> catsAndDogs;
    /* But when the time complexity is the necessary one then we could use a separate list
     * for Cats and Dogs and then take decision we could use peek to not remove but to check
     * which one is the latest */
    private LinkedList<Cat> cats;
    private LinkedList<Dog> dogs;

    public Shelter() {
        this.cats = new LinkedList<>();
        this.dogs = new LinkedList<>();
    }

    public void enqueue(Animal animal) {
        if (animal instanceof Dog) {
            this.dogs.offer((Dog) animal);
            return;
        }
        this.cats.offer((Cat) animal);
    }

    public Animal dequeueAny() {
        if (this.cats.isEmpty() && this.dogs.isEmpty()) {
            System.out.println("No animals available... please try later.");
            return null;
        }
        Cat firstCat = this.cats.peek();
        Dog firstDog = this.dogs.peek();
        return firstCat == null ? this.dogs.poll() :
                firstDog == null ? this.cats.poll() :
                        (Long.compare(firstCat.getArrivedDate(), firstDog.getArrivedDate()) < 0) ? this.cats.poll() : this.dogs.poll();
    }

    public Cat dequeueCat() {
        if (this.cats.isEmpty()) {
            System.out.println("No cats available... would you want to adopt a dog?");
            return null;
        }
        return this.cats.poll();
    }

    public Dog dequeueDog() {
        if (this.dogs.isEmpty()) {
            System.out.println("No dogs available... would you want to adopt a cat?");
            return null;
        }
        return this.dogs.poll();
    }

    public void show() {

    }
}

class Animal {
    private long arrivedDate;
    private String name;

    Animal(long arrivedDate, String name) {
        this.arrivedDate = arrivedDate;
        this.name = name;
    }

    long getArrivedDate() {
        return arrivedDate;
    }

    public String getName() {
        return name;
    }
}

class Dog extends Animal {
    /* Dogs specific implementation like bark, roll, play, sleep */

    public Dog(long arrivedDate, String name) {
        super(arrivedDate, name);
    }
}

class Cat extends Animal {
    /* Cats specific implementation like pur, play, sleep */

    public Cat(long arrivedDate, String name) {
        super(arrivedDate, name);
    }
}