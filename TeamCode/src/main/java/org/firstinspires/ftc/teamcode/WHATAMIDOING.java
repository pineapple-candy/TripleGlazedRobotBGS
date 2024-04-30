package org.firstinspires.ftc.teamcode;

public class WHATAMIDOING {
    public enum State {
        STATE_ONE,
        STATE_TWO,
        STATE_THREE
    }
    public static void main(String[] args)  {
        State state = State.STATE_TWO;
        switch (state) {
            case STATE_ONE:
                break;
            case STATE_TWO:
                break;
            case STATE_THREE:
                break;

        }
    }
}
