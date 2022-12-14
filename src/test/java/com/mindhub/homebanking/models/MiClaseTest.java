package com.mindhub.homebanking.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiClaseTest {

    @Test
    void suma() {
        MiClase miClase = new MiClase();
        assertEquals(4,miClase.suma(2,2));
    }
}