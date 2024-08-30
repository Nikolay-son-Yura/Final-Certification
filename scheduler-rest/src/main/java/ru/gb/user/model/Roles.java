package ru.gb.user.model;

public enum Roles {

   USER("ROLE_USER"),ADMIN("ROLE_ADMIN");
    private String name;

    Roles(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
