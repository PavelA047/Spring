package com.example;

import java.util.Map;

public class Application {
    public static ApplicationContext run(String package2Scan, Map<Class, Class> ifc2ImplClass) {
        ConfigImpl config = new ConfigImpl(package2Scan, ifc2ImplClass);
        ApplicationContext applicationContext = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(applicationContext);
        applicationContext.setFactory(objectFactory);
        return applicationContext;
    }
}
