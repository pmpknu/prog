import events.EventException;
import scene.Scene;

public class Main {
    public static void main(String[] args) {
        Scene s = new Scene();
        try {
            s.build();
        } catch (EventException e) {
            System.out.println(e.getMessage());
        }
    }
}