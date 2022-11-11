package com.iot.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "parked_car_number_plate")
    private String parkedCarNumberPlate;
    @ManyToOne
    @JoinColumn(name = "customer_card_id", referencedColumnName = "id", nullable = false)
    private CustomerCard customerCard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParkedCarNumberPlate() {
        return parkedCarNumberPlate;
    }

    public void setParkedCarNumberPlate(String parkedCarNumberPlate) {
        this.parkedCarNumberPlate = parkedCarNumberPlate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id) && Objects.equals(parkedCarNumberPlate, booking.parkedCarNumberPlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parkedCarNumberPlate);
    }

    public CustomerCard getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(CustomerCard customerCard) {
        this.customerCard = customerCard;
    }
}
