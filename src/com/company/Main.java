package com.company;

import java.util.Arrays;

public class Main {

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
    public static long forCombinations(int steps, final int STEPA, final int STEPB){
        System.out.println(" A "+STEPA+" B "+STEPB+" for steps "+steps);
        int m=0;
        int n=0;///
        int x=0;
        long permuts=0;
        while(STEPA*n+STEPB*m<=steps && (n*m)-m>=0){
            m = x;
            n = (-STEPB*m +steps)/STEPA;
            System.out.println(" n "+n+" m "+m +" add "+(STEPA*n+STEPB*m));
            //compute number of permutations for that combination given by et he formula n!/k!
            long permut=(factorial(steps)/factorial(n))/factorial(m);//modified instead of factorial(20)/(factorial(n)*factorial(k));
            System.out.println(" whose permut is "+permut);
            permuts+=permut;
            x++;
        }
        return permuts;
    }

    public static void main(String[] args) {
	// write your code here
        System.out.println("\n Number of ways it could jump: "+(forCombinations(20, 1,2)+1));///we just do for 20 then add 1 as th last step we can only do 1 as the number ahs to be even to satisfy the relationship k=1n+2m with whole numbers only
    }
}
