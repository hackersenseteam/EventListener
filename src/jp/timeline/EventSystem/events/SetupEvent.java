package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;

public class SetupEvent extends EventCore {
    public SetupEvent() {
        super(Events.SETUP);
    }
}
