package com.tekpyramid.reservation.repository;

import com.tekpyramid.reservation.data.models.entities.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus, String> {

}
