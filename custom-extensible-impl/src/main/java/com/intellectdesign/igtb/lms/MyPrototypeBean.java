package com.intellectdesign.igtb.lms;

import java.time.LocalDateTime;

public class MyPrototypeBean {

    private String dateTimeString = LocalDateTime.now().toString();
    public MyPrototypeBean() {
		System.out.println("MyPrototypeBean created : "+dateTimeString);
	}

    public String getDateTime() {
        return dateTimeString;
    }
}