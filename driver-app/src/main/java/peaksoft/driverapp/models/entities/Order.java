package peaksoft.driverapp.models.entities;

import lombok.Getter;
import lombok.Setter;
import peaksoft.driverapp.models.enums.Status;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

import static javax.persistence.CascadeType.*;

/**
 * @author Beksultan
 */
@Entity
@Table(name = "orders")
@Getter @Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    private Client client;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Address addressA;

    @ManyToOne(cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private Address addressB;

    @ManyToOne(cascade = {MERGE, DETACH, REFRESH})
    private Driver driver;

    private BigDecimal price;

    private Double distance;

    @Enumerated(EnumType.STRING)
    private Status status;
}
