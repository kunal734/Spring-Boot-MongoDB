package com.tekpyramid.reservation.dao;

import com.tekpyramid.reservation.data.models.entities.Bus;

import java.util.Optional;

public interface BusDao {

    public Bus saveBus(Bus bus);

    public Optional<Bus> findBusById(String id);
}
