package com.example;

import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

public class ConfigImpl implements Config {

    @Getter
    private Reflections scanner;
    private Map<Class, Class> ifc2ImplClass;

    public ConfigImpl(String packageToScan, Map<Class, Class> ifc2ImplClass) {
        this.scanner = new Reflections(packageToScan);
        this.ifc2ImplClass = ifc2ImplClass;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        return ifc2ImplClass.computeIfAbsent(ifc, aClass -> {
            if (classes.size() != 1) {
                throw new RuntimeException("There are no implementations, or more then 1 implementation");
            }
            return classes.iterator().next();
        });
    }
}
