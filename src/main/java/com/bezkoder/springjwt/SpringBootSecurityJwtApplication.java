package com.bezkoder.springjwt;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.bezkoder.springjwt.security.services.FilesStorageService;


@SpringBootApplication

public class SpringBootSecurityJwtApplication extends SpringBootServletInitializer implements CommandLineRunner{
	@Resource
	  FilesStorageService storageService;
	@Autowired
	private JavaMailSender javaMailSender;
//	void sendEmail() {
//	SimpleMailMessage msg = new SimpleMailMessage();
//	msg.setTo("taha.taha303@gmail.com");
//	msg.setSubject("Testing from Spring Boot");
//	msg.setText("Hello World \n Spring Boot Email");
//	javaMailSender.send(msg);
//	}
//	
	public static String uploadDirectory =
			System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	public static void main(String[] args) {
		new File(uploadDirectory).mkdir();

		SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}
	
//	@Override
//    public void run(String... args) throws MessagingException, IOException {
//
//        System.out.println("Sending Email...");
//
//        sendEmail();
//		
//
//        System.out.println("Done");
//
//    }

	
	@Override
	  public void run(String... arg) throws Exception,MessagingException,
		IOException {
//		 sendEmail();
	    storageService.deleteAll();
	   storageService.init();
	  }

}
