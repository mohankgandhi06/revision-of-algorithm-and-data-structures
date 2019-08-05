package com.revision.ctci.gobjectorienteddesign;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BCallCenter {

    /* A Call is to be assigned to a employee.
     * ? According to the requirement a person will not be able to attend the call, so does that mean
     *   we have to assign the call randomly to each respondent rather than finding the least busy one and do not care if he/she is free or not?
     *   In such a case there might be more instance of calls being diverted to higher people and the callers waiting even though there are other
     *   respondents who might be free and this might not be as efficient as it can be. Ideally we would need to implement a queue and then
     *   monitor the list of free respondents | managers | director and go on a hierarchical basis. In this case there is no requirement on the
     *   employee to forward it upwards to the next official.
     * ? Is there a need to auto escalate the call if the employee is not able to respondent for a certain time for some reason
     * ? What if none of the employee is free. We should implement a call back mechanism or call waiting mechanism
     * ? If one set of employee is all busy but their higher official are free in that case if we are going to find the person in the
     *   lower queue it will create a feel that everyone is busy and make the caller wait even though someone above is technically free */

    private CircularQueue<Respondent> respondents;
    private CircularQueue<Manager> managers;
    private CircularQueue<Director> directors;

    private CircularQueue<Caller> callers;

    public HashMap<Integer, CircularQueue> employees;

    public BCallCenter() {
        this.employees = new HashMap<>();
        gatherEmployeeList();
        this.callers = new CircularQueue<>(new Caller[ 50 ]);
    }

    private void gatherEmployeeList() {
        Respondent[] respondents = (Respondent[]) fetch("Respondent");
        Manager[] managers = (Manager[]) fetch("Manager");
        Director[] directors = (Director[]) fetch("Director");
        if (respondents != null) {
            this.respondents = new CircularQueue<>(respondents);
            this.employees.put(this.respondents.queue[ 0 ].level, this.respondents);
        }
        if (managers != null) {
            this.managers = new CircularQueue<>(managers);
            this.employees.put(this.managers.queue[ 0 ].level, this.managers);
        }
        if (directors != null) {
            this.directors = new CircularQueue<>(directors);
            this.employees.put(this.directors.queue[ 0 ].level, this.directors);
        }
    }

    /* This could be even refined by using the database call at the start of the Construction and fetch the list
     * of employee and also the hard coded employee type can be modified */
    private Employee[] fetch(String employeeType) {
        if (employeeType.equalsIgnoreCase("Respondent")) {
            return respondents();
        } else if (employeeType.equalsIgnoreCase("Manager")) {
            return managers();
        } else if (employeeType.equalsIgnoreCase("Director")) {
            return directors();
        }
        return null;
    }

    private Respondent[] respondents() {
        return new Respondent[]{
                new Respondent("Alec", 1),
                new Respondent("Alex", 2),
                new Respondent("Amy", 3),
                new Respondent("Amdy", 4),
                new Respondent("Britney", 5),
                new Respondent("Brad", 6),
                new Respondent("Bronx", 7),
                new Respondent("Carrey", 8),
                new Respondent("Carthy", 9),
                new Respondent("Celine", 10),
                new Respondent("Dan", 11),
                new Respondent("Denver", 12),
                new Respondent("Daisy", 13),
                new Respondent("Disney", 14),
                new Respondent("Elizabeth", 15),
                new Respondent("Ema", 16),
                new Respondent("Evan", 17),
                new Respondent("Fred", 18),
                new Respondent("Fanny", 19),
                new Respondent("Forrest", 20),
                new Respondent("Gary", 21),
                new Respondent("Gimmy", 22),
                new Respondent("Gordon", 23),
                new Respondent("Gremit", 24),
                new Respondent("Hudson", 25),
                new Respondent("Hitler", 26),
                new Respondent("Hillary", 27),
                new Respondent("Hitman", 28),
                new Respondent("Indiana", 29),
                new Respondent("Irvin", 30),
                new Respondent("Imman", 31),
                new Respondent("John", 32),
                new Respondent("Jhonny", 33),
                new Respondent("Jane", 34),
                new Respondent("Jenny", 35),
                new Respondent("Kelly", 36),
                new Respondent("Kenedy", 37),
                new Respondent("Larry", 38),
                new Respondent("London", 39),
                new Respondent("Martin", 40),
                new Respondent("Mandy", 41),
                new Respondent("Minolet", 42),
                new Respondent("Mary", 43),
                new Respondent("Nina", 44),
                new Respondent("Nancy", 45),
                new Respondent("Nash", 46),
                new Respondent("Norton", 47),
                new Respondent("Ortiz", 48),
                new Respondent("Oliver", 49),
                new Respondent("Owen", 50)
        };
    }

    private Manager[] managers() {
        return new Manager[]{
                new Manager("Antony", 51),
                new Manager("Abraham", 52),
                new Manager("Amanda", 53),
                new Manager("Barry", 54),
                new Manager("Brian", 55),
                new Manager("Jessica", 56),
                new Manager("Jim", 57),
                new Manager("Justin", 58),
                new Manager("Kim", 59),
                new Manager("Kristine", 60)
        };
    }

    private Director[] directors() {
        return new Director[]{
                new Director("Denver", 61),
                new Director("Roger", 62),
                new Director("Emmy", 63),
                new Director("Zack", 64),
                new Director("Efron", 65),
        };
    }

    public static void main(String[] args) {
        BCallCenter callCenter = new BCallCenter();
        callCenter.start();
        callCenter.dispatchCall();
    }

    /* IMPLEMENT A THREAD FOR CREATING THE WORKING ENVIRONMENT OF A CALL CENTER
     * WHERE THE CALLER QUEUE IS MAINTAINED */
    private void start() {
        CallCenter thread = new CallCenter();
        thread.start();
    }

    private void dispatchCall() {
        int level = 1;
        while (this.employees.containsKey(level)) {
            if (level == Rank.RESPONDENT.getValue()) {
                if (((Respondent) this.employees.get(level).queue[ 0 ]).canAttendCall()) {
                    /* MOVE HIM TO THE END OF THE QUEUE AND START THE CALL */
                    ((Respondent) this.employees.get(level).queue[ 0 ]).attendCall();
                }
            } else if (level == Rank.MANAGER.getValue()) {
                if (((Manager) this.employees.get(level).queue[ 0 ]).canAttendCall()) {
                    /* MOVE HIM TO THE END OF THE QUEUE AND START THE CALL */
                    ((Manager) this.employees.get(level).queue[ 0 ]).attendCall();
                }
            } else if (level == Rank.DIRECTOR.getValue()) {
                if (((Director) this.employees.get(level).queue[ 0 ]).canAttendCall()) {
                    /* MOVE HIM TO THE END OF THE QUEUE AND START THE CALL */
                    ((Director) this.employees.get(level).queue[ 0 ]).attendCall();
                }
            } else {
                System.out.println("Line is Busy. Please wait for some time...");
                /* Add to the queue again and try again after some interval */
                /* When we are using thread we have to be careful we will not get into infinite loop
                 * deadlock trylock and other conditions */
            }
            level++;
        }
    }
}

/* If the Implementation of the method for each employee differs then ideally it is good if the method are declared abstract like below
 * If not then the actual common implementation can be done in the Employee Class and avoid duplicating in the subclass
 * Our assumption is that we are going to have various implementation and so we can created the abstract methods */
abstract class Employee {
    public long id;
    public String name;
    private int mobile;
    private String email;
    private Date joiningDate;
    private int experienceInYears;
    public int level;

    public Employee() {

    }

    public Employee(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public abstract boolean canAttendCall();

    public abstract void attendCall();

    public abstract boolean dispatchCall();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }
}

class Respondent extends Employee {

    public Respondent(String name, long id) {
        this.name = name;
        this.id = id;
        this.level = Rank.RESPONDENT.getValue();
    }

    public boolean canAttendCall() {
        return false;
    }

    public void attendCall() {

    }

    /* IF NOT ABLE TO DISPATCH CALL TO */
    public boolean dispatchCall() {
        return false;
    }
}

class Manager extends Employee {
    public Manager(String name, long id) {
        this.level = Rank.MANAGER.getValue();
    }

    public boolean canAttendCall() {
        return false;
    }

    public void attendCall() {

    }

    /* IF NOT ABLE TO DISPATCH CALL TO */
    public boolean dispatchCall() {
        return false;
    }
}

class Director extends Employee {
    public Director(String name, long id) {
        this.level = Rank.DIRECTOR.getValue();
    }

    public boolean canAttendCall() {
        return false;
    }

    public void attendCall() {

    }

    /* IF NOT ABLE TO DISPATCH CALL TO */
    public boolean dispatchCall() {
        return false;
    }
}

class Caller {
    public long id;
    public String name;
    public int mobile;

    public Caller(String name, int mobile) {
        this.id = generateId();
        this.name = name;
        this.mobile = mobile;
    }

    /* IMPLEMENT AN ALGORITHM FOR GENERATING THE CALLER ID */
    private long generateId() {
        return -1;
    }
}

class CallCenter extends Thread {
    @Override
    public void run() {
        /* Manage the two queues CALLER and EMPLOYEE[respondent|manager|director] */
        while (true) {
            try {
                /* This is to just fill up the logic has to be devised */
                TimeUnit.MILLISECONDS.sleep(1000);
                interrupt();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted... ");
            }
        }
    }
}

enum Rank {
    RESPONDENT(1), MANAGER(2), DIRECTOR(3);

    private int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

class CircularQueue<T> {
    public T[] queue;
    private int size;
    private int topIndex;

    public CircularQueue(T[] array) {
        this.queue = array;
        this.size = array.length;
        this.topIndex = 0;
    }

    /* IMPLEMENTATION OF THE CIRCULAR LIST IS OUT OF SCOPE */
}