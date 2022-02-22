import jp.timeline.EventSystem.EventListener;
import jp.timeline.EventSystem.Events;
import jp.timeline.EventSystem.events.SetupEvent;

public class Setup {
    @EventListener(event = Events.SETUP)
    public void onSetup1()
    {
        System.out.println("Setup 1");
    }

    @EventListener
    public void onSetup2(SetupEvent event)
    {
        System.out.println("Setup 2 | " + event.getType().getName() + " | " + event.getText());
    }

    @EventListener(event = Events.SETUP)
    public void onSetup3(SetupEvent event)
    {
        System.out.println("Setup 3 | " + event.getType().getName() + " | " + event.getText());
    }
}
