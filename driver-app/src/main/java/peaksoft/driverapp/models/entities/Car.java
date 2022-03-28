package peaksoft.driverapp.models.entities;

import peaksoft.driverapp.models.enums.EngineType;

import javax.persistence.*;
import java.awt.*;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String brand;

    private String model;

    private String numberOfCar;

    private String color;

    private int yearOfIssue;

    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    @OneToOne(cascade = {MERGE, REFRESH})
    private Driver carOwner;

}