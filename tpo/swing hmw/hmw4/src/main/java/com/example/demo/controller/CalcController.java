package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalcController {

    @PostMapping("/convert")
    @ResponseBody
    public String convertNumber(@RequestParam int value,
                                @RequestParam int fromBase,
                                @RequestParam int toBase) {
        return convertBase(value, fromBase, toBase);
    }

    private String convertBase(int value, int fromBase, int toBase) {
        int decimalValue = Integer.parseInt(String.valueOf(value), fromBase);
        String result = Integer.toString(decimalValue, toBase);

        StringBuilder conversionResult = new StringBuilder();
        conversionResult.append("Original Value: ").append(value).append(" (Base ").append(fromBase).append(")<br>");
        conversionResult.append("Decimal Value: ").append(decimalValue).append("<br>");
        conversionResult.append("Conversion Result (Base ").append(toBase).append("): ").append(result).append("<br>");
        conversionResult.append("BIN Representation: ").append(Integer.toBinaryString(decimalValue)).append("<br>");
        conversionResult.append("OCT Representation: ").append(Integer.toOctalString(decimalValue)).append("<br>");
        conversionResult.append("DEC Representation: ").append(decimalValue).append("<br>");
        conversionResult.append("HEX Representation: ").append(Integer.toHexString(decimalValue).toUpperCase()).append("<br>");

        return conversionResult.toString();
    }
}
