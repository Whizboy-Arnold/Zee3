package com.company;

import java.util.Arrays;

public class Main {
    static final boolean debug=true;

    static long[] memo= new long[20];//this will greatly increase speed and avoid kurudia computing the same thing. IMPORTANT DO NOT USE VALUES ABOVE 20 WITH LONG.  AND ALSO HELPS US SEE OUR FACTORIALS AND ALSO AIDS TO SEE WHEN THERE IS AN OVERFLOW AND LIMIT IT TO 20 HENCE  we wl get array overflow error wch we can instead of arithmetic overflow which is hard to detect.. we also cant use anyu number greater than 20 as we wl requrie big integer or other library for storing largerfactorials
    static long factorial(int n){
        if (n == 0)
            return 1;
        else{
            if(memo[n-1]>0){
                return memo[n-1];
            }else{
                memo[n-1] = (n * factorial(n-1));
                if(debug)if(debug)System.out.println(" memo updated to " + Arrays.toString(memo));
                return memo[n-1];//(n * factorial(n-1));
            }
        }
    }
    static long computePermuts(int steps, final int STEPA, final int STEPB){
        if(debug)System.out.println("computing for A "+STEPA+" B "+STEPB+" for steps "+steps);

        int m=0;
        int n=0;///
        int x=0;
        long permuts = 0;
        while ((n * m) - m >= 0) {
            if(debug)System.out.println(x);
            m = x;
            n = (-Math.max(STEPA, STEPB)  * m + steps) / Math.min(STEPA, STEPB);
            long permut;

            int sum = Math.min(STEPA, STEPB) * n + Math.max(STEPA, STEPB) * m;
            if(m==0 || n ==0){
                if(debug)System.out.println(" n " + n + " m " + m + " add " + sum);
                permut= 1;//// for th 2 cases it is similar all through
            }else {
                if(debug)System.out.println("__n " + n + " m " + m + " add " + sum);
                permut = (factorial(m+n) / factorial(n)) / factorial(m);//m+n forms th number of digits forming th line up. also modified instead of factorial(20)/(factorial(n)*factorial(k));  so just in case of large numbers  of m and n it is still manageable
            }
            if(debug)System.out.println(" whose permut is " + permut); assert permut>0;///if this is less probably is an overflow not caught
            permuts += permut;
            x++;///initial was righ no dont ad min  tht was stupid
        }
        return permuts;
    }

    //because K = 1n+2m; where K is 20, and n and m are 1 the numbers used
    public static long forCombinations(int steps, final int STEPA, final int STEPB){
        if(debug)System.out.println(" A "+STEPA+" B "+STEPB+" for steps "+steps);

        if(Math.min(STEPA,STEPB) > 0 ){
            if (STEPA != STEPB) { ///if it is only fully divisible by one of the pair  or is odd  and other number is 1(as ev WCH IS covered by the odd number test we remove the smaller one eg give 4 and 2 but yet steps are 22. for th last step one only 2 is applicable ALSO THE REMAINDER OF TH MODULO FO TH LARGE ONE MST BE SERVICABLE BY SMALLER ONES
                if(steps % STEPA==0 || steps % STEPB==0) {///THEN FURTHER VALIDATE THAT THE NUMBER OF STEPS IS A FACTOR OF TH BOTH OF THE STEPS ( not including a step fo one as forms a single pattern so all odd numbers wl hve 1 added to last permut) otherwise it wont be perfectly divided by such and we wl have to miitigate such.
                    int largeREM = steps % Math.max(STEPA, STEPB);
                    if( largeREM % Math.min(STEPA,STEPB) == 0)  {///if the remainder of divison of larger is dividible by smaller
                        // the trailing  non divisibles by smaller cause a  slight problem wch should be solved with the below modificaiton
                        if(largeREM<1 )return  computePermuts(steps, STEPA, STEPB); ///the combinations are pretty straight forward
                        else{////AS THINK ABOUT IT, assume u have the traIling 1after combination say consider 23 over 5 and 1. u remain wth 3 after ths combination, it oculd b inserted in any  ofset  within th space ofseting th fited one  or being in th e middle of it  any of its steps and is like  as if padded with 1s and th othe rnumber is the number so  i cross multiply each side  so to asume each side can fith onto space of th other
                            long main = computePermuts(steps - largeREM, STEPA, STEPB);
                            long extra = factorial(steps / Math.max(STEPA, STEPB) + largeREM / Math.min(STEPA, STEPB))
                                    / (factorial(steps / Math.max(STEPA, STEPB)) * factorial(largeREM / Math.min(STEPA, STEPB)));
                            if(debug)System.out.println(" A "+STEPA+" B "+STEPB+" trailing " +largeREM+" of permut "+extra+ " was multiplied to "+main+" as was not a perfect fit to make it  " +(main*extra));
                            return  main*extra;
                        }
                    }else{
                        throw new UnsupportedOperationException(" not possible to jump with whole number of steps even when using smaller ones for the remainder portion ");
                    }

                }else{
                    throw new UnsupportedOperationException(" no support for fractional steps or partial permutation spaces as both do not divide the space wholly ");
                }
            } else {
                throw new UnsupportedOperationException(" no same of a kind jumps supported as otherwise arrangements for such is just 1 ");///if they are same whichever the pattern is only a single type
            }
        } throw new UnsupportedOperationException(" no empty jumps supported ");
    }

    public static void main(String[] args) {
	    // write your code here
        long time= System.currentTimeMillis();
        System.out.println("\n\n Number of ways it could jump is : "+(forCombinations(21, 7,1)));// eg for 20 values  2,4; 2,1; 8,4; AND 6,2  and variants with 1 and any other number  from 2 to 20  ARE APPLICABLE.  now no need for special case for 21 or others the algorithm supports all kinds as long as posible to be divided by so
        // for this question values 21 , 1 and 2 are used. .
        System.out.println(" program has taken "+(System.currentTimeMillis()-time));/// memoizing reduces the time almost by half :) from ave of 40 ms to 20 ms on an i7 where the half is almost entirely due to the output statements. , also memoizing helps it to be done in one huge chunk by taking advantage of that earlier tiem it doe sso isntead of reduing it subsequently.
    }
}
