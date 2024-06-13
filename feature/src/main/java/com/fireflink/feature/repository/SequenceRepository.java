package com.fireflink.feature.repository;

import com.fireflink.feature.models.entity.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String> {
}
