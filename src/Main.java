import jp.timeline.EventSystem.EventManager;
import jp.timeline.EventSystem.events.SetupEvent;

public class Main {
    public static void main(String[] args) {
        EventManager.addListener(new Setup());
        EventManager.call(new SetupEvent());
    }
}
