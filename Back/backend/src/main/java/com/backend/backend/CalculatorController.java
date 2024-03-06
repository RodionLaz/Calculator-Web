package com.backend.backend;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class CalculatorController {


    @RequestMapping("/calculate")
    public float hello(@RequestBody String numbers){
        float result =0;
        try{
            System.out.println(numbers);
            result= calculate(numbers);
            System.out.println(result);
        }catch(Exception e){
            result =0;
        }
        return result;
    }

    public float[] calculateHelper(int charloc,char op,String numbers,float num1){
        int h=charloc+1;
        float num2;
        String num2str="";
        float[] finalAndLoc = new float[2];

        while (h < numbers.length() && (Character.isDigit(numbers.charAt(h)) || numbers.charAt(h) == '.')){
            num2str += numbers.charAt(h);
            h++;
        }
        finalAndLoc[1] = h-1;
        num2 = Float.parseFloat(num2str);
        switch (op){
            case '×':
                finalAndLoc[0] =num1*num2;
                break;
            case '÷':
                if (num2 != 0) {
                    finalAndLoc[0] = num1 / num2;
                }else{
                    finalAndLoc[0] = 0;
                }
                break;
            case '+':
                finalAndLoc[0] =num1+num2;
                break;
            case '-':
                finalAndLoc[0] =num1-num2;
                break;
            default:
                finalAndLoc[0] = 0;
        }
        return finalAndLoc;
    }
    public float calculate(String numbers){
        numbers = numbers.replaceAll(" ", "");
       // float[] results = sograim(numbers);
       // if (Float.toString(results[0]) != ""){
       //     numbers = numbers.substring(0, (int) results[1]) + results[0] + numbers.substring((int) results[2] + 1);
        //}
        float num1;
        String  str="",str2="";
        float[] finalAndLoc;
        int start=0,end=0;
        int h = 0;
        while (h < numbers.length()) {
            char b = numbers.charAt(h);
            if(Character.isDigit(b)|| b =='.') {
                str += b;
            }
            if (b == '+' || b == '-') {
                str = "";
                start = h + 1;
            }
            if (b == '×' || b == '÷') {
                num1 = Float.parseFloat(str);
                finalAndLoc = calculateHelper(h, b, numbers, num1);
                end = (int) finalAndLoc[1];
                numbers = numbers.substring(0, start) + finalAndLoc[0] + numbers.substring(end + 1);
                h = 0;
                str = "";
            }else{
                h++;
            }
        }
        start = 0;
        int i = 0;
        while (i < numbers.length()) {
            char c = numbers.charAt(i);
            if (!Character.isDigit(c)) {
                if(c=='.'){str2+=c;i++;}
                if (c == '+'||c == '-') {
                    while(Character.isDigit(c)){
                        i =0;
                    }
                    finalAndLoc = calculateHelper(i, c, numbers, Float.parseFloat(str2));
                    end = (int) finalAndLoc[1];
                    numbers= numbers.substring(0, start) + finalAndLoc[0] + numbers.substring(end +1);
                    i = 0;
                    str2 = "";
                }
            } else {
                str2 += c;
                i++;
            }
        }
        return Float.parseFloat(numbers);
}
}



