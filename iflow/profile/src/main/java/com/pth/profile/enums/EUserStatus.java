package com.pth.profile.enums;

public enum EUserStatus {

    NOT_INITIALIZED(0),
    ACTIVE(1),
    LOCKED(10);


    private int id;

    EUserStatus(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
