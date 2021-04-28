import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private int current_stage;
    private String name;
    private CyclicBarrier car_cb;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isFinish() {
        if ( current_stage == race.getStages().size()) { return true;}
        return false;}

    public void incCarStage() { current_stage++;}

    public Car(Race race, int speed, CyclicBarrier car_cb) {
        this.race = race;
        this.speed = speed;
        this.current_stage = 0;
        this.car_cb = car_cb;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {

        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            //System.out.println(this.car_cb.getNumberWaiting());
            this.car_cb.await();

        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
            if (!Main.iswinner & isFinish() ) {
                System.out.println(name + " - WIN");
                Main.iswinner = true;
            }
        }
    }
}
