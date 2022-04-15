package com.example;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class injectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> properties;

    @SneakyThrows
    public injectPropertyAnnotationObjectConfigurator() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        this.properties = lines.map(line -> line.split("="))
                .collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @SneakyThrows
    @Override
    public void configure(Object o, ApplicationContext applicationContext) {
        Class<?> implClass = o.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            InjectProperty annotation = field.getAnnotation(InjectProperty.class);


            if (annotation != null) {
                String value;
                if (annotation.value().isEmpty()) {
                    value = properties.get(field.getName());
                } else {
                    value = properties.get(annotation.value());
                }
                field.setAccessible(true);
                field.set(o, value);
            }
        }
    }
}
