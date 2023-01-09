package entities.spaceobjects.spaceship.engine;

import details.EngineEl;
import details.Time;
import events.EventException;

public interface EngineFeatures {
    public String turnOn(Time time) throws EventException;

    public String turnOff(Time time);

    public String sounds();

    public String getElement(EngineEl el);
}
