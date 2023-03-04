package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "KUNDE", schema = "PUBLIC")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "VORNAME", length = 100, nullable = false)
    private String firstName;

    @Column(name = "NACHNAME", length = 100, nullable = false)
    private String lastName;

    // @Temporal(TemporalType.DATE)
    @Column(updatable = false)
    private LocalDate creationDate = LocalDate.now();

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false)
    private LocalDateTime changeDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private CustomerType type = CustomerType.NEW;

    @Transient
    private Date readDate = new Date();

    public Customer() {
        super();
        creationDate = LocalDate.now();
    }

    public Customer(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Date getReadDate() {
        return readDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getChangeDate() {
        return changeDate;
    }
}