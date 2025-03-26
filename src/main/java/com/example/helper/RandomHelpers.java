package com.example.helper;


import java.util.UUID;

public class RandomHelpers {

    public static String random() {
        return UUID.randomUUID().toString().replace("-","");
    }

}
