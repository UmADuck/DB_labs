package com.iot.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "company_card", schema = "korol_iot_14", catalog = "")
public class CompanyCard {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "company_name")
    private String companyName;
    @OneToMany(mappedBy = "companyCard")
    private List<CustomerCard> customerCards;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyCard that = (CompanyCard) o;
        return Objects.equals(id, that.id) && Objects.equals(companyName, that.companyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName);
    }

    public List<CustomerCard> getCustomerCards() {
        return customerCards;
    }

    public void setCustomerCards(List<CustomerCard> customerCards) {
        this.customerCards = customerCards;
    }
}
