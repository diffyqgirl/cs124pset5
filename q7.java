import java.util.Arrays;
public class q7
{
    static final int BINS = 1000000000;
    static final int BALLS = 1000000000;
    static final int MBINS = 20; //number of macrobuckets
    static final int TRIALS = 10 ;
    static String mode;
    public static void main(String[] args)
    {
        if (args.length != 1 || (!args[0].equals("a") && !args[0].equals("b")))
        {
            System.out.println("Usage: java q7 mode, where mode is a or b for part a or part b");
            System.exit(1);
        }
        mode = args[0];
        long[] mbins_avg = new long[MBINS]; // will have the values of mbins averaged over TRIALS trials
        for (int i = 0; i < mbins_avg.length; i++)
            mbins_avg[i] = 0;
        for (int k = 0; k < TRIALS; k++)
        {
            System.out.println("Running trial " + k);
            int[] mbins = new int[MBINS];
            mbins[0] = BINS; //initially, all bins are empty
            for (int i = 1; i < mbins.length; i++)
            {
                mbins[i] = 0; 
            }
            int bin;
            int count;
            for (int j = 0; j < BALLS; j++)
            {
                bin = get_rand(); //which bin the jth ball is going in
                count = 0;
                for (int i = 0; i < mbins.length; i++)
                {
                    count += mbins[i];
                    if (bin < count) // this ball is going into a bin with i balls already in it
                    {
                        mbins[i] -= 1;
                        mbins[i+1] += 1; // we'll make the probability of index out of bounds small here, which is good enough
                        break;
                    }
                }
            }
            for (int i = 0; i < mbins.length; i++)
            {
                mbins_avg[i] += mbins[i];
            }
        }
        // print results
        System.out.println("Results are averaged over " + TRIALS + " trials.");
        for (int i = 0; i < mbins_avg.length; i++)
        {
            if (mbins_avg[i] != 0)
            {
                System.out.println(mbins_avg[i]/TRIALS + " bins have " + i + " balls.");
            }
        }

    }
    public static int get_rand()
    {
        if (mode.equals("a"))
            return (int)(Math.random() * BINS);
        return Math.min((int)(Math.random() * BINS), (int)(Math.random() * BINS));
    }

}