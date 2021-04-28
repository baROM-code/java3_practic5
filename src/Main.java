import java.util.concurrent.CyclicBarrier;

public class Main {

    public static final int CARS_COUNT = 5;
    public static boolean iswinner = false;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        CyclicBarrier cbcars = new CyclicBarrier(CARS_COUNT, new StartRace());

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cbcars);
        }

        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

        // Ждем завершения потоков  (другой способ не придумал,
        while (Thread.activeCount() > 2) {  }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

    }
}

