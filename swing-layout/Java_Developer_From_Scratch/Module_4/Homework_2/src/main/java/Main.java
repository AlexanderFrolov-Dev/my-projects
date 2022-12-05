public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.add("Coffee", 30);
        basket.add("Juice", 50, 3, 3);
        basket.add("Cola", 60, 5, 7.5);
        basket.add("Tea", 20, 2);
        basket.print("Milk");
        System.out.println("TotalWeight: " + basket.getTotalWeight());
    }
}
