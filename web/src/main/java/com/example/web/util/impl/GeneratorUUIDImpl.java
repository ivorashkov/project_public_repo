package com.example.web.util.impl;

import com.example.web.util.GeneratorUUID;

import java.util.UUID;


public class GeneratorUUIDImpl implements GeneratorUUID {

    private final UUID id;

    public GeneratorUUIDImpl() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String generateUUID() {
        return this.id.toString();
    }
}
