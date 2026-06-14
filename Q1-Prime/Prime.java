/* ------------------------------------------------ ------------
File: Prime.java

Last name: Le, Colasante
Student number: 300417781, 300427831

Description: This program creates a child thread that outputs all prime numbers less than or equal to the number entered by the user.

------------------------------------------------------------- */

public class Prime extends Thread {
    private int maxNumber;
    private static int maxEnteredNumber;

    public Prime(int maxNumber){
        this.maxNumber = maxNumber;
    }

    @Override
    public void run(){
        for (int number = 2; number <= maxNumber; number++) {
            if (this.isPrime(number)){
                System.out.print(number + " ");
            }
        }
        System.out.println();
    }

    private boolean isPrime(int number){
        if (number < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Please input a valid number.");
            return;
        }

        try{
            maxEnteredNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer.");
            return;
        }

        if (maxEnteredNumber < 0){
            System.out.println("Please enter a non-negative number.");
            return;
        }

        Prime childThread = new Prime(maxEnteredNumber);

        System.out.println("Prime numbers less than or equal to " + maxEnteredNumber + ":");
        childThread.start();

        try{
            childThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted.");
        }

    }
}