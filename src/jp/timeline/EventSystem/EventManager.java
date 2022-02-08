package jp.timeline.EventSystem;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager
{
    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();
    private static final CopyOnWriteArrayList<Object> listeners = new CopyOnWriteArrayList<>();

    public static void reset()
    {
        listeners.clear();
    }

    public static void addListener(Object... projects)
    {
        for (Object object : projects)
            if (!listeners.contains(object))
                listeners.add(object);
    }

    public static void removeListener(Object... projects)
    {
        if (!listeners.isEmpty())
            Arrays.asList(projects).forEach(listeners::remove);
    }

    public static EventCore call(EventCore event) {
        listeners.forEach(listener -> {
            for (Method method : listener.getClass().getDeclaredMethods())
            {
                if (!method.isAccessible())
                    method.setAccessible(true);

                if (method.isAnnotationPresent(EventListener.class))
                {
                    if (method.getParameterCount() > 1)
                        throw new EventException("[EventSystem] too many method types! method name:" + method.getName());

                    try
                    {
                        MethodHandle handle = EventManager.lookup.unreflect(method);
                        Events events = method.getDeclaredAnnotation(EventListener.class).event();

                        if (events != Events.NONE && (events == event.getEvents() || events == Events.ALL))
                        {
                            if (method.getParameterCount() == 1)
                            {
                                Class<?> methodObject = method.getParameterTypes()[0];
                                if (methodObject != event.getClass())
                                    throw new EventException("[EventSystem] The event does not match! method name:" + method.getName());
                                handle.invoke(listener, event);
                            }
                            else
                            {
                                handle.invoke(listener);
                            }
                        }
                        else if (events == Events.NONE && method.getParameterCount() == 1)
                        {
                            Class<?> methodObject = method.getParameterTypes()[0];

                            if (methodObject != null && methodObject == event.getClass())
                                handle.invoke(listener, event);
                        }
                        else if (events == event.getEvents())
                        {
                            throw new EventException("Incorrect usage! method name:" + method.getName());
                        }
                    }
                    catch (InvocationTargetException | IllegalAccessException ignored)
                    {

                    }
                    catch (Throwable throwable)
                    {
                        throwable.printStackTrace();
                    }
                }
            }
        });

        return event;
    }
}