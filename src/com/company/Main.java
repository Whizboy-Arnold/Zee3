package com.company;

public class Main {
    final int STEP1=1;
    final int STEP2=1;

    static int[] memo= new int[20];
    static int factorial(int n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n-1));
    }

    //because K = 1n+2m; where K is 20, and n and m are 1 the numbers used
    public static void forCombinations(int steps){
        int m=0;
        int n=0;///
        int x=0;
        while(n+2*m<=20 && (n*m)-m>=0){
            m = x;
            n = -2*m +20;
            System.out.println(" n "+n+" m "+m +" add "+(n+2*m));

            x++;
        }
    }



    public static void main(String[] args) {
	// write your code here
        forCombinations(20);
    }
}
