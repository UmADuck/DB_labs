package com.iot.dao.impl;

import com.iot.dao.CompanyCardDao;
import com.iot.domain.CompanyCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class CompanyCardDaoImpl implements CompanyCardDao {

    private static final String FIND_ALL = "SELECT * FROM company_card";
    private static final String CREATE = "INSERT company_card(company_name) VALUES (?)";
    private static final String UPDATE = "UPDATE company_card SET company_name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM company_card WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM company_card WHERE id=?";
    private static final String FIND_BY_COMPANY_NAME = "SELECT * FROM company_card WHERE company_name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<CompanyCard> findByCompanyName(String companyName) {
        Optional<CompanyCard> companyCard;
        try {
            companyCard = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_COMPANY_NAME,
                    BeanPropertyRowMapper.newInstance(CompanyCard.class), companyName));
        } catch (EmptyResultDataAccessException e) {
            companyCard = Optional.empty();
        }
        return companyCard;
    }

    @Override
    public List<CompanyCard> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(CompanyCard.class));
    }

    @Override
    public Optional<CompanyCard> findById(Integer id) {
        Optional<CompanyCard> companyCard;
        try {
            companyCard = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(CompanyCard.class), id));
        } catch (EmptyResultDataAccessException e) {
            companyCard = Optional.empty();
        }
        return companyCard;
    }

    @Override
    public int create(CompanyCard entity) {
        return jdbcTemplate.update(CREATE, entity.getCompanyName());
    }

    @Override
    public int update(Integer id, CompanyCard entity) {
        return jdbcTemplate.update(UPDATE, entity.getCompanyName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
