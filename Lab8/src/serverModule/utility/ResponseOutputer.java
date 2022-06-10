package serverModule.utility;

public class ResponseOutputer {
    private static StringBuilder stringBuilder = new StringBuilder();

    public static void append(Object o) {
        stringBuilder.append(o + "\n");
    }

    public static String getString() {
        return stringBuilder.toString();
    }

    public static String getAndClear() {
        String toReturn = stringBuilder.toString();
        stringBuilder.delete(0, stringBuilder.length());
        return toReturn;
    }
}
