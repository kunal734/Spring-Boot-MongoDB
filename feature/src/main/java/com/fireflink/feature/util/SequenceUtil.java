package com.fireflink.feature.util;

import com.ff.support_portal.dto.CommonConstants;
import com.ff.support_portal.model.Sequence;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.sound.midi.Sequence;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Component
@AllArgsConstructor
public class SequenceUtil {
    private final MongoTemplate mongoTemplate;
    private static final String SEQUENCE = "ticket_seq";

    public String generateSequence() {
        final int initValue = 10000;
        return null;
    }

}
