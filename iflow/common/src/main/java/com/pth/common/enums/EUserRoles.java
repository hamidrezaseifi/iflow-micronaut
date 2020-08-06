package com.pth.common.enums;

public enum EUserRoles {
    ADMIN("ADMIN"),
    DATAENTRY("DATAENTRY"),
    VIEW("VIEW");


    private  String id;

    EUserRoles(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
