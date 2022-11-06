package com.iot.dao.impl;

import com.iot.dao.ParkingSpotsInfoDao;
import com.iot.domain.ParkingSpotsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("SqlResolve")
@Service
public class ParkingSpotsInfoDaoImpl implements ParkingSpotsInfoDao {

    private static final String FIND_ALL = "SELECT * FROM parking_spots_info";
    private static final String CREATE = "INSERT parking_spots_info(parking_id) VALUES (?)";
    private static final String UPDATE = "UPDATE parking_spots_info SET parking_id=? WHERE id=?";
    private static final String DELETE = "DELETE FROM parking_spots_info WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM parking_spots_info WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ParkingSpotsInfo> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ParkingSpotsInfo.class));
    }

    @Override
    public Optional<ParkingSpotsInfo> findById(Integer id) {
        Optional<ParkingSpotsInfo> parkingSpotsInfo;
        try {
            parkingSpotsInfo = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(ParkingSpotsInfo.class), id));
        } catch (EmptyResultDataAccessException e) {
            parkingSpotsInfo = Optional.empty();
        }
        return parkingSpotsInfo;
    }

    @Override
    public int create(ParkingSpotsInfo entity) {
        return jdbcTemplate.update(CREATE, entity.getParkingId());
    }

    @Override
    public int update(Integer id, ParkingSpotsInfo entity) {
        return jdbcTemplate.update(UPDATE, entity.getParkingId(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
