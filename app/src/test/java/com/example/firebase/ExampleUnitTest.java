package com.example.firebase;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleUnitTest {
    @Test
    public void IsValid()
    {
        AuthValidation fieldsCriteries = new AuthValidation();

        assertEquals(true, fieldsCriteries.isValid("88005553535",
                "proshe_pozvonit@mail.com", "chemYkogotoZanimat!2",
                "chemYkogotoZanimat!2"));
    }
}