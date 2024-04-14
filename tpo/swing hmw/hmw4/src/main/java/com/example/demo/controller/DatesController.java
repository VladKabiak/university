package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Controller
public class DatesController {


    @GetMapping("/current-time")
    @ResponseBody
    public String getCurrentTime(@RequestParam(required = false) String timeZone,
                                 @RequestParam(required = false) String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format != null ? format : "HH:mm:ss yyyy/MM/dd");
            if (timeZone != null) {
                sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
            }
            return sdf.format(new Date());
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage() + "\nDefault time: " + getDefaultTime();
        }
    }

    @GetMapping("/current-year")
    @ResponseBody
    public String getCurrentYear() {
        try {
            return String.valueOf(java.time.Year.now().getValue());
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage() + "\nDefault time: " + getDefaultTime();
        }
    }

    private String getDefaultTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSSS yyyy/MM/dd");
        return sdf.format(new Date());
    }
}
