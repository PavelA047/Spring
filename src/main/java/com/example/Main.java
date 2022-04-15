package com.example;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = Application.run("com.example", new HashMap<>(Map.of(Policeman.class, AngryPoliceman.class)));
        CoronaDesinfector coronaDesinfector = applicationContext.getObject(CoronaDesinfector.class);
        coronaDesinfector.start(new Room());
    }
}
