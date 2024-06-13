package com.tekpyramid.reservation.dao.impl;

import com.tekpyramid.reservation.dao.BusDao;
import com.tekpyramid.reservation.data.models.entities.Bus;
import com.tekpyramid.reservation.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BusDaoImpl implements BusDao {

    private final BusRepository busRepository;

    @Override
    public Bus saveBus(Bus bus){
        return busRepository.save(bus);
    }

    @Override
    public Optional<Bus> findBusById(String id) {
        return busRepository.findById(id);
    }
}
