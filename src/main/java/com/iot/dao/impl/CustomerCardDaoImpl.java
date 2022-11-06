package com.iot.dao.impl;

import com.iot.dao.CustomerCardDao;
import com.iot.domain.CustomerCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("SqlResolve")
@Service
public class CustomerCardDaoImpl implements CustomerCardDao {

    private static final String FIND_ALL = "SELECT * FROM customer_card";
    private static final String CREATE = "INSERT customer_card(customer_name, customer_surname, city_name) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE customer_card SET customer_name=?, customer_surname=?, city_name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM customer_card WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM customer_card WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CustomerCard> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(CustomerCard.class));
    }

    @Override
    public Optional<CustomerCard> findById(Integer id) {
        Optional<CustomerCard> customerCard;
        try {
            customerCard = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(CustomerCard.class), id));
        } catch (EmptyResultDataAccessException e) {
            customerCard = Optional.empty();
        }
        return customerCard;
    }

    @Override
    public int create(CustomerCard entity) {
        return jdbcTemplate.update(CREATE, entity.getCustomerName(), entity.getCustomerSurname(), entity.getCityName());
    }

    @Override
    public int update(Integer id, CustomerCard entity) {
        return jdbcTemplate.update(UPDATE, entity.getCustomerName(), entity.getCustomerSurname(), entity.getCityName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
