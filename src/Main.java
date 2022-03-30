import jp.timeline.EventSystem.EventManager;
import jp.timeline.EventSystem.events.SetupEvent;
import jp.timeline.EventSystem.type.EventType;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread("Shutdown")
        {
            @Override
            public void run() {
                EventManager.reset();
            }
        });

        EventManager.addListener(new Setup());
        EventManager.call(new SetupEvent("PRE", EventType.PRE));
        System.out.println("Call");
        EventManager.call(new SetupEvent("POST", EventType.POST));
    }
}
