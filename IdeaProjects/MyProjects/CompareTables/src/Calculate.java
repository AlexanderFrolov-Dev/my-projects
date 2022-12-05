import java.math.BigDecimal;

public class Calculate {
    public static void main(String[] args) {
        double a = 0.1;
        double b = 0.2;
        boolean equals = (a + b) == 0.3;
        System.out.println(equals);
        BigDecimal a1 = BigDecimal.valueOf(0.1);
        BigDecimal b1 = BigDecimal.valueOf(0.2);
        boolean equals1 = a1.add(b1).equals(BigDecimal.valueOf(0.3).doubleValue());
        System.out.println(equals1);
    }
}
