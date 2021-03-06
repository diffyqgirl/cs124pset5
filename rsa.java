import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
/*
Code by 30943147 and 50996336
*/
public class rsa
{
    public static void main(String[] args)
    {
        String msg = "Give me an A";
        String bin_msg = "";
        BigInteger n = new BigInteger("46947848749720430529628739081");
        BigInteger e = new BigInteger("37267486263679235062064536973");
        for (int i = 0; i < msg.length(); i++)
        {
            String temp = Integer.toBinaryString((int) msg.charAt(i));
            while(temp.length() < 8)
            {
                temp = "0" + temp;
            }
            bin_msg += temp;
        }
        BigInteger m = new BigInteger(bin_msg, 2);
        System.out.println("m = " + m); 
        // break up e into powers of 2, and throw the relevent powers into a list
        ArrayList<Integer> pows = new ArrayList<Integer>();
        int max_pow = ((int) log2(e));
        pows.add(max_pow);
        BigInteger temp_e = e;
        int temp_pow = max_pow;
        temp_e = temp_e.subtract((new BigInteger("2").pow(temp_pow)));
        //while(temp_e > BigInteger.ZERO)
        while(temp_e.compareTo(BigInteger.ZERO) == 1)
        {
            //temp_e -= Math.pow(2, temp_pow);
            
            temp_pow = ((int) log2(temp_e));
            pows.add(temp_pow);
            temp_e = temp_e.subtract((new BigInteger("2").pow(temp_pow)));
        }
        System.out.println("Pows: " + Arrays.toString(pows.toArray()));
        BigInteger[] mpows = new BigInteger[max_pow + 1];
        mpows[0] = m;
        for (int i = 1; i <= max_pow; i++)
        {
            //mpows[i] = (mpows[i-1]*mpows[i-1])%n;
            mpows[i] = mpows[i-1].modPow(new BigInteger("2"), n);
        }

        BigInteger answer = BigInteger.ONE;
        for (Integer i: pows)
        {
            //answer = (answer * mpows[i])%n;
            answer = answer.multiply(mpows[i]).mod(n);
        }
        System.out.println("answer = " + answer);
    
    }

    // returns floor of log2 x
    public static int log2(BigInteger x)
    {
        return x.bitLength() - 1;
    }


}