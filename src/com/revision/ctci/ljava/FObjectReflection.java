package com.revision.ctci.ljava;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FObjectReflection {
    /* Reflection */
    /* The Java API that allows an Object who class/method/field/arguments (private/public) is unknown
     * to be analysed so that we could use it to invoke the call by instantiating it */
    /* Lets consider that an object of type Movie is passed to a method that does not know what is present
     * beneath it */
    public static void main(String[] args) {
        String name = "Robo 2.0";
        List<Actor> actors = new ArrayList<>(Arrays.asList(new Actor("Rajni", 65), new Actor("Amy Jackson", 30), new Actor("Akshay", 40)));
        List<CameraMan> cameraMen = new ArrayList<>(Arrays.asList(new CameraMan("PC Sriram", 50)));
        List<Technician> technicians = new ArrayList<>(Arrays.asList(new Technician("Venkat", 47), new Technician("Steven", 30)));
        Director director = new Director("Shankar", 45);
        Movie movie = new Movie(name, actors, cameraMen, technicians, director);
        makeMovie(movie);
    }

    private static void makeMovie(Object movie) {
        Class cls = movie.getClass();
        System.out.println("Class: ");
        System.out.println(cls.getSimpleName());
        System.out.println("_________________________________");
        System.out.println("Constructors: ");
        Constructor[] constructors = cls.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor.toGenericString());
        }
        System.out.println("_________________________________");
        System.out.println("Methods: ");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(" - Name: " + method.getName());
            System.out.print(" - Parameters: ");
            Arrays.stream(method.getGenericParameterTypes()).forEach(type -> System.out.print(type.getTypeName() + " "));
            System.out.println();
            System.out.println(" - Return Type: " + method.getReturnType().getSimpleName());
            System.out.println();
        }
        try {
            /* Public Method */
            Method direct = cls.getDeclaredMethod("direct");
            direct.invoke(movie);
            /* Accessing Private Method */
            Method lighting = cls.getDeclaredMethod("lighting", int.class);
            lighting.setAccessible(true);
            lighting.invoke(movie, 8);
        } catch (NoSuchMethodException e) {
            System.out.println("No such Method exists...");
        } catch (Exception e) {
            System.out.println("Exception Occured");
        }
        System.out.println("_________________________________");
        System.out.print("Fields: ");
        Arrays.stream(cls.getDeclaredFields()).forEach(field -> System.out.print(field.getName() + " "));
        System.out.println();
        try {
            Field field = cls.getDeclaredField("name");
            field.setAccessible(true);
            field.set(movie, "Chiti 2.0");
            System.out.println("Movie name changed to: " + ((Movie) movie).getName());
        } catch (NoSuchFieldException e) {
            System.out.println("No such Field exists...");
        } catch (Exception e) {
            System.out.println("Exception Occured");
        }
    }
}

class Movie {
    private String name;
    private List<Actor> actors;
    private List<CameraMan> cameraMen;
    private List<Technician> technicians;
    private Director director;
    private Story story;

    public Movie(String name, List<Actor> actors, List<CameraMan> cameraMen, List<Technician> technicians, Director director) {
        this.name = name;
        this.actors = actors;
        this.cameraMen = cameraMen;
        this.technicians = technicians;
        this.director = director;
    }

    public void direct() {
        this.director.take();
        for (int i = 0; i < 20; i++) {
            lighting(i);
            System.out.println("Scene #" + i + ": ");
            System.out.println("The shot is good!!!");
            System.out.println("next one...");
        }
        this.director.packup();
    }

    private void lighting(int scene) {
        this.technicians.get(0).lighting(scene);
    }

    public String getName() {
        return name;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<CameraMan> getCameraMen() {
        return cameraMen;
    }

    public List<Technician> getTechnicians() {
        return technicians;
    }

    public Director getDirector() {
        return director;
    }
}

class Story {
    private String story;

    public Story(String story) {
        this.story = story;
    }

    public String getStory() {
        return story;
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Actor extends Person {
    public Actor(String name, int age) {
        super(name, age);
    }

    public void act() {
        System.out.println("Acting");
    }
}

class CameraMan extends Person {
    public CameraMan(String name, int age) {
        super(name, age);
    }

    public void capture() {
        System.out.println("Capturing");
    }
}

class Technician extends Person {
    public Technician(String name, int age) {
        super(name, age);
    }

    public void edit() {
        System.out.println("Editing");
    }

    public void lighting(int scene) {
        System.out.println("Lighting for scene: " + scene);
    }

    public void shooting() {
        System.out.println("Shooting");
    }
}

class Director extends Person {
    public Director(String name, int age) {
        super(name, age);
    }

    public void take() {
        System.out.println("Take");
    }

    public void packup() {
        System.out.println("Pack Up");
    }
}