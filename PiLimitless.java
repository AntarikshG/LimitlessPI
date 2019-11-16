import java.math.BigInteger;
import java.util.Scanner;

public class PiLimitless {

    static int PiSeries[];

    public static void  LimitlessPiUsingNilkantha(int precision, long iteration)
    {

        for (long term=1 ; term<iteration;term++)
        {
            FillPiWithoutlimit(term, precision);
            if(term%1000000==0)
            {
                System.out.print("Value of Pi for term:" +term +"  Is : " );
                for (int j=0;j<precision;j++)
                    System.out.print(PiSeries[j]);
                System.out.println(" ");
            }
        }

    }
    private static void FillPiWithoutlimit(long term, long precision)
    {

        int sign=1;
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
                PiSeries[i] = PiSeries[i]+ sign* divident.intValue();
                numerator=numerator.subtract(denominator.multiply(divident));
                numerator=numerator.multiply(BigInteger.valueOf(10));
                if(numerator.equals(BigInteger.valueOf(0))) break;

                if(PiSeries[i]<0  ) {
                    PiSeries[i]=PiSeries[i]+10;
                    PiSeries[i-1]=PiSeries[i-1]-1;
                }
                if(PiSeries[i]>9  ) {
                    PiSeries[i-1]=PiSeries[i-1]+1;
                    PiSeries[i]=PiSeries[i]-10;
                }

            }
        }
    }



    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        int numb;
        long x,y,precision,iteration;
        long startTime , elapsedTime ;
        // For comparison
        String PiValue ="3.14159265358979323846264338327950288419716939937510582097494459230781640628620";

        System.out.println("Enter precision");
        // String input
        numb = myObj.nextInt();


        System.out.println("Enter Iteration: Max limit 100 Crore , crashed on 1000 crore ");

        // String input
        iteration = myObj.nextInt();
        PiSeries = new int[numb];

        startTime = System.nanoTime();
        LimitlessPiUsingNilkantha(numb,iteration);

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




