package com.example;

public class CoronaDesinfector {

    @InjectByType
    private Announcer announcer;

    @InjectByType
    private Policeman policeman;

    public void start(Room room) {
        announcer.announce("всем выйти");
        policeman.makePeopleLeaveRoom();
        desinfect(room);
        announcer.announce("заходите");
    }

    private void desinfect(Room room) {
        System.out.println("дезинфекция началась");
    }
}
