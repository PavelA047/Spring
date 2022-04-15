package com.example;

@Singleton
public class RecommendatorImpl implements Recommendator {

    @InjectProperty(value = "cognac")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("drink " + alcohol);
    }
}
