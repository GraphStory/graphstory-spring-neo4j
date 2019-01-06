package com.graphstory.service;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;

import java.util.Date;
import java.util.UUID;

public class GraphStoryService {

    public Long dateAsLong() {

        return new Date().getTime();
    }

    public final TimeBasedGenerator uuidGenerator = Generators.timeBasedGenerator();

    public String uuidGen() {
        final UUID uuid = uuidGenerator.generate();
        final StringBuilder sb = new StringBuilder();
        sb.append(Long.toHexString(uuid.getMostSignificantBits())).append(Long.toHexString(uuid.getLeastSignificantBits()));

        return sb.toString();
    }
}
