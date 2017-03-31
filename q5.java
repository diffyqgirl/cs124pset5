import java.math.BigInteger ;
import java.util.Arrays;
import java.util.ArrayList;

/*
Code by 30943147 with collaboration from 50996336 
*/
public class q5
{
    public static void main(String [] args)
    {
        // define this as a constant for use later
        BigInteger TWO = new BigInteger("2");

        //input
        BigInteger n = new BigInteger("294409");
        BigInteger e = new BigInteger("73602");
        
        BigInteger m = TWO; // this is a
        // break up e into powers of 2, and throw the relevent powers into a list
        ArrayList<Integer> pows = new ArrayList<Integer>();
        int max_pow = ((int) log2(e));
        pows.add(max_pow);
        BigInteger temp_e = e;
        int temp_pow = max_pow;
        temp_e = temp_e.subtract((TWO.pow(temp_pow)));

        while(temp_e.compareTo(BigInteger.ZERO) == 1)
        {
            temp_pow = ((int) log2(temp_e));
            pows.add(temp_pow);
            temp_e = temp_e.subtract((TWO.pow(temp_pow)));
        }

        BigInteger[] mpows = new BigInteger[max_pow + 1];
        mpows [0] = m;
        for (int i = 1; i <= max_pow; i++)
        {
            mpows[i] = mpows[i - 1].modPow(TWO, n);
        }

        BigInteger answer = BigInteger.ONE ;
        for (Integer p : pows) {
            answer = answer.multiply(mpows[p]).mod(n);
        }
        System.out.println("ans is " + answer);
        System.out.println("ans^2 mod n is " + answer.multiply(answer).mod(n));
    
    }

    // returns floor of log2 x, helper method
    public static int log2(BigInteger bi)
    {
        return bi.bitLength() - 1;
    }

}