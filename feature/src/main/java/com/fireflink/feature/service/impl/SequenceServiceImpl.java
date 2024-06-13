package com.fireflink.feature.service.impl;

import com.fireflink.feature.service.SequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SequenceServiceImpl implements SequenceService {

    private final MongoOperations mongoOperations;

    @Override
    public long generateSequence(String sequence) {
//        if(mongoOperations)
        return 0;
        
    }
}
