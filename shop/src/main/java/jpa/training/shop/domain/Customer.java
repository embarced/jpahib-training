package jpa.training.shop.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Address> addresses = new HashSet<>();

    @OneToOne
    private User user;

    @OneToOne(cascade = { CascadeType.ALL })
    private Address billingAddress;

    @OneToMany(mappedBy = "customer", cascade = { CascadeType.ALL })
    private List<BillingDetail> billingDetails = new ArrayList<BillingDetail>();

    public Customer() {
        super();
        creationDate = LocalDate.now();
    }

    public Customer(String firstName, String lastName, User user) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
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

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public void addToBillingDetails(BillingDetail billingDetail) {
        billingDetail.setCustomer(this);
        this.billingDetails.add(billingDetail);
    }
    public void addToAddresses(Address address) {
        addresses.add(address);
        address.setCustomer(this);
    }
}
