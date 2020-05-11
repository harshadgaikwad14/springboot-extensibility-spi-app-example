package com.intellectdesign.igtb.lms;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

public class MySingletonBean {
	private String dateTimeString = LocalDateTime.now().toString();

	public MySingletonBean() {
		System.out.println("MySingletonBean created : " + dateTimeString);
	}

	@Autowired
	private MyPrototypeBean prototypeBean;

	public void showMessage() {
		System.out.println(">>>> Hi, the time is " + prototypeBean.getDateTime());
	}
}