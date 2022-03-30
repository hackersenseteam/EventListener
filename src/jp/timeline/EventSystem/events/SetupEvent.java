package jp.timeline.EventSystem.events;

import jp.timeline.EventSystem.EventCore;
import jp.timeline.EventSystem.Events;
import jp.timeline.EventSystem.type.EventType;

public class SetupEvent extends EventCore {
    private final String text;

    public SetupEvent(String text, EventType type) {
        super(Events.SETUP);
        this.setType(type);
        this.text = text;
    }

    public String getText()
    {
        return this.text;
    }
}