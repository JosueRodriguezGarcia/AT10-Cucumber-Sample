package com.fundation.cucumber;

public class FizzBuzz {
    public String play(int number){
        String res;
        if(number == 0 ) throw new IllegalArgumentException("Number must not be 0");
        if(isMultipleOf(number,3)){
            if( isMultipleOf(number,5)){
                return "FizzBuzz";
            }
            return "Fizz";
        }else if(isMultipleOf(number, 5)){
            return "Buzz";
        }else{
            return String.valueOf(number);
        }
    }
    private boolean isMultipleOf(int number, int i){
        return number%i == 0;
    }
}
