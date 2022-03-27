package peaksoft;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Optional<Integer> integerOptional = Optional.ofNullable(getNumber());
        int value = integerOptional.orElseThrow(MyException::new);
        System.out.println(value);
    }

    public static Integer getNumber() {
        Random random = new Random();
        return switch (random.nextInt(2)) {
            case 0 -> null;
            case 1 -> random.nextInt(1000);
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(2));
        };
    }

    static class MyException extends RuntimeException {}
}
