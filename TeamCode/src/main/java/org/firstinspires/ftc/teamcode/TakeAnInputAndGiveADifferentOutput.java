package org.firstinspires.ftc.teamcode;
//import help.me

public class TakeAnInputAndGiveADifferentOutput {
    //Now, where to start?
    //Let's see, we'll get the input first, and then process it.
    //...But where from?
    //...I know! the terminal!
    //That's a little too hard, (wasn't first result of google search) let's try something else.
    //An integer!!! For this purpose we'll set the integer to 13 (an unlucky number).
    static int Suffering = 0;

    private static void getALife(int headaches, int childhoodTraumas, int optimism) {
        Suffering += headaches;
        Suffering += childhoodTraumas;
        Suffering /= optimism;
    }

    private static void happyGuy() {
        getALife(4,1,5);

        System.out.println("Suffering: " + Suffering);
    }

    private static void sadGuy() {
        getALife(15,3,2);

        System.out.println("Suffering: " + Suffering);
    }

    private static void thatOneGuy() {
        getALife(0,9999999,500000);

        System.out.println("Suffering: " + Suffering);
    }

}
