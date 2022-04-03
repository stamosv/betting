package com.example.bettingproject.shared.enums;


public enum SportEnum {
    Football("1"),
    Basketball("2");

    private String name;

    SportEnum(String type) {
        this.name = type;
    }

    public String getName() {
        return name;
    }

    public static String getEnumByString(String code) {
        for(SportEnum e : SportEnum.values()) {
            if(e.name.equals(code))
                return e.name();
        }
        return null;
    }


}
