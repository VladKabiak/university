package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	@GetMapping("/")
	public String showMainPage() {
		StringBuilder conversionResult = new StringBuilder();

		conversionResult.append("<h1>add /conversion to url to convert value </h1> <br>");
		conversionResult.append("<h1>add /current-time to see current time </h1> <br>");
		conversionResult.append("<h1>add /current-time?timeZone=America/New_York to see current time in new york</h1> <br>");
		conversionResult.append("<h1>add /current-year to see current year</h1> <br>");
		return conversionResult.toString();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
