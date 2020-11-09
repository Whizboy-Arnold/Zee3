package com.company;

import java.util.Arrays;

public class Main {
    final int STEP1=1;
    final int STEP2=1;

    static long[] memo= new long[20];//this will greatly increase speed and avoid kurudia computing the same thing. we also cant use anyu number greater than 20 as we wl requrie big integer or other library for storing largerfactorials
    static long factorial(int n){
        if (n == 0)
            return 1;
        else{
            if(memo[n-1]>0){
                return memo[n-1];
            }else{
                memo[n-1] = (n * factorial(n-1));
                System.out.println(" memo updated to " + Arrays.toString(memo));
                return memo[n-1];
            }
        }
    }

    //because K = 1n+2m; where K is 20, and n and m are 1 the numbers used
    public static long forCombinations(int steps){
        int m=0;
        int n=0;///
        int x=0;
        long permuts=0;
        while(n+2*m<=20 && (n*m)-m>=0){
            m = x;
            n = -2*m +20;
            System.out.println(" n "+n+" m "+m +" add "+(n+2*m));
            //compute number of permutations for that combination given by et he formula n!/k!
            long permut=factorial(20)/factorial(n)*factorial(m);
            System.out.println(" whose permut is "+permut);
            permuts+=permut;
            x++;
        }
        return permuts;
    }



    public static void main(String[] args) {
	// write your code here
        System.out.println("number of ways : "+forCombinations(20));
    }
}
