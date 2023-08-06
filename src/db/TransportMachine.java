package db;

import java.util.Vector;

public class TransportMachine {
    private int id;
    private String make;
    private String model;
    private String category;
    private String registration_number;
    private String machine_type;
    private int year;
    private boolean trailer;

    @Override
    public String toString() {
        return "TransportMachine{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", category='" + category + '\'' +
                ", registration_number='" + registration_number + '\'' +
                ", machine_type='" + machine_type + '\'' +
                ", year=" + year +
                ", trailer=" + trailer +
                '}';
    }

    public Vector<String> toVector(){
        Vector<String> vec = new Vector<>(8);
        vec.add(String.valueOf(id));
        vec.add(make);
        vec.add(model);
        vec.add(category);
        vec.add(registration_number);
        vec.add(machine_type);
        vec.add(String.valueOf(year));
        vec.add(String.valueOf(trailer));
        return vec;
    }

    public TransportMachine(){

    }

    public TransportMachine(int id, String make, String model, String category, String registration_number, String machine_type, int year, boolean trailer){
        this.id = id;
        this.make = make;
        this.model = model;
        this.category = category;
        this.registration_number = registration_number;
        this.machine_type = machine_type;
        this.year = year;
        this.trailer = trailer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRegistration_number() {
        return registration_number;
    }

    public void setRegistration_number(String registration_number) {
        this.registration_number = registration_number;
    }

    public String getMachine_type() {
        return machine_type;
    }

    public void setMachine_type(String machine_type) {
        this.machine_type = machine_type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean hasTrailer() {
        return trailer;
    }

    public void setTrailer(boolean trailer) {
        this.trailer = trailer;
    }
}
