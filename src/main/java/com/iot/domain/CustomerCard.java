package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer_card", schema = "korol_iot_14", catalog = "")
public class CustomerCard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "customer_name")
    private String customerName;
    @Basic
    @Column(name = "customer_surname")
    private String customerSurname;
    @OneToMany(mappedBy = "customerCard")
    private List<Booking> bookings;
    @ManyToOne
    @JoinColumn(name = "company_card_id", referencedColumnName = "id")
    private CompanyCard companyCard;
    @OneToMany(mappedBy = "customerCard")
    private List<ParkingTicket> parkingTicket;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerCard that = (CustomerCard) o;
        return Objects.equals(id, that.id) && Objects.equals(customerName, that.customerName) && Objects.equals(customerSurname, that.customerSurname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerSurname);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public CompanyCard getCompanyCard() {
        return companyCard;
    }

    public void setCompanyCard(CompanyCard companyCard) {
        this.companyCard = companyCard;
    }

    public List<ParkingTicket> getParkingTicket() {
        return parkingTicket;
    }

    public void setParkingTicket(List<ParkingTicket> parkingTicket) {
        this.parkingTicket = parkingTicket;
    }
}
