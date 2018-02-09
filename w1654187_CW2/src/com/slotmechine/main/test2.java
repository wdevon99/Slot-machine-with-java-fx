package com.slotmechine.main;

public class test2 {

    public static void main(String[] args) {


        double payoutPercentage =0;

        //Probability of getting all three symbols matching (for each symbol)
        double allThreePossiblity= (1.0/216.0 );
        //Probability of getting two symbols matching (for each symbol)
        double twoPossiblity =(1.0/36.0);

        double valuesOfSymbols [] = {7,6,5,4,3,2};

        double totalPay=0;

        for (int i=0 ;  i<valuesOfSymbols.length; i++ ){

            //adding up to make the total pay
            totalPay+= (valuesOfSymbols[i]*allThreePossiblity) + (valuesOfSymbols[i] * twoPossiblity);

        }

        //multiplying by 100 to get the percentage pay out
        payoutPercentage = (totalPay)*100;

        System.out.println(payoutPercentage);


    }
}
