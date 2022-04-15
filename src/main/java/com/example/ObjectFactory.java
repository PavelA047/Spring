package com.example;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {

    private final ApplicationContext applicationContext;
    private List<ObjectConfigurator> objectConfiguratorList = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        for (Class<? extends ObjectConfigurator> aClass : applicationContext.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            objectConfiguratorList.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T t = implClass.getDeclaredConstructor().newInstance();

        objectConfiguratorList.forEach(objectConfigurator -> objectConfigurator.configure(t, applicationContext));

        return t;
    }
}
