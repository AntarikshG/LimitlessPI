import java.math.BigInteger;
import java.util.Scanner;

public class PiLimitless {

    static int PiSeries[];

    public static void  LimitlessPiUsingNilkantha(int precision, long iteration)
    {

        for (long term=1 ; term<iteration;term++)
        {
            if(FillPiWithoutlimit(term, precision)==1) {
                System.out.println("Precision reached at Iteration "+ term);
                break;}
            //CorrectPiSeries(precision);
            if(term%1000000==0)
            {
                CorrectPiSeries(precision);
                System.out.print("Value of Pi for term:" +term +"  Is : " );
                for (int j=0;j<precision;j++)
                    System.out.print(PiSeries[j]);
                System.out.println(" ");
            }
        }

    }

    private static void CorrectPiSeries(long precision)
    {
        for(int j=1;j<precision;j++) {
            while (PiSeries[j] < 0 || PiSeries[j] > 9) {
                if (PiSeries[j] < 0) {
                    PiSeries[j] = PiSeries[j] + 10;
                    PiSeries[j - 1] = PiSeries[j - 1] - 1;

                }
                if (PiSeries[j] > 9) {
                    PiSeries[j - 1] = PiSeries[j - 1] + 1;
                    PiSeries[j] = PiSeries[j] - 10;

                }
                j--;
            }
        }

    }
    private static int  FillPiWithoutlimit(long term, long precision)
    {

        int sign=1;
        int written =0;
        if (term%2 ==0) {
            sign = 1;
        } else
        {
            sign = -1;
        }

        //Below statments convert x,y to positive values if they are negative
        BigInteger numerator, denominator, divident;

        numerator = BigInteger.valueOf(4);
        denominator = BigInteger.valueOf(2*term);
        denominator=denominator.multiply(BigInteger.valueOf(2*term-1));
        denominator=denominator.multiply(BigInteger.valueOf(2*term-2));

        if(term==1)
        {
            PiSeries[1]=3;
        }
        else
        {
            for(int i=1;i<precision;i++)
            {
                divident = numerator.divide(denominator);
                // System.out.print(divident);
                if(!divident.equals(BigInteger.valueOf(0)))
                {
                    PiSeries[i] = PiSeries[i]+ sign* divident.intValue();
                    written =1;
                }

                numerator=numerator.subtract(denominator.multiply(divident));
                numerator=numerator.multiply(BigInteger.valueOf(10));
                if(numerator.equals(BigInteger.valueOf(0))) break;

            }

            if(written==0)
            {
                return 1;
            }
        }
        return 0;
    }



    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int numb;
        long x,y,precision,iteration;
        long startTime , elapsedTime ;
        // For comparison
        String PiValue ="3.14159265358979323846264338327950288419716939937510582097494459230781640628620";

        System.out.println("Enter precision - Please be aware precision above 22 will take million+ of iterations");
        // String input
        numb = myObj.nextInt();
        //Increasing by 2 to ensure correct digits till numb-2
        numb=numb+2;

        //System.out.println("Enter Iteration ");

        // String input - By default iterating till infinity - crore crore iterations
        //iteration = myObj.nextLong();
        iteration = 100000000000000;
        PiSeries = new int[numb];

        startTime = System.nanoTime();
        LimitlessPiUsingNilkantha(numb,iteration);
        CorrectPiSeries(numb);
        System.out.println("Final value of Pi");
        for (int i=0;i<numb;i++)
            System.out.print(PiSeries[i]);
        elapsedTime = System.nanoTime() - startTime;
        System.out.println("");
        System.out.println("Total execution time to create value of Pi in MilliSeconds: "
                + elapsedTime/1000000 + "Precision" + numb);
        System.out.println("Actual value of Pi"+ PiValue);

    }
}



//Changes proposed
//1. Display number in decimal
 //2.Display till precision calculated

