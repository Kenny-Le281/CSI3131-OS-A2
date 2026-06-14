/* ------------------------------------------------ ------------
File: FibonacciSeries.java

Last name: Le, Colasante
Student number: 300417781, 300427831

Description: Description: This program creates a child thread to generate a Fibonacci sequence. 
The child thread stores the sequence in a shared array, and the parent thread waits for it to finish before printing the result.

------------------------------------------------------------- */

public class FibonacciSeries extends Thread {
    private int n;
    private long[] fibonacciSequence;

    public FibonacciSeries(int n, long[] fibonacciSequence){
        this.n = n;
        this.fibonacciSequence = fibonacciSequence;
    }

    @Override
    public void run() {
        if (n >= 1) {
            fibonacciSequence[0] = 0;
        }

        if (n >= 2) {
            fibonacciSequence[1] = 1;
        }

        for (int i = 2; i < n; i++) {
            fibonacciSequence[i] = fibonacciSequence[i - 1] + fibonacciSequence[i - 2];
        }
    }

    public static void main(String[] args){
        if (args.length != 1){
            System.out.println("Please input a valid number. Example: java FibonacciSeries 8");
            return;
        }

        int n = Integer.parseInt(args[0]);

        if (n < 0){
            System.out.println("Please enter a non-negative number.");
            return;
        }

        long[] fibonacciSequence = new long[n];

        FibonacciSeries childThread = new FibonacciSeries(n, fibonacciSequence);

        childThread.start();

        try{
            childThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }

        System.out.println("Fibonacci sequence:");

        for (int i = 0; i < n; i++){
            System.out.print(fibonacciSequence[i] + " ");
        }

        System.out.println();
    }
}