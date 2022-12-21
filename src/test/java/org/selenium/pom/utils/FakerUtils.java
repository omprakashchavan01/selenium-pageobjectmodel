package org.selenium.pom.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public Long generateRandomNumber(){
        Faker faker = new Faker();
        return faker.number().randomNumber(10, true);
    }

    public String generateRandomName(){
        Faker faker = new Faker();
        return faker.name().fullName();
    }
}
