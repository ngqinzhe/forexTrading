package RunProgram;

public class UnixTimeCurrent {

    public static String currentTime() {
        long unixTime = System.currentTimeMillis() / 1000L;
        return Long.toString(unixTime);
    }
}
