package entities.spaceobjects.spaceship.engine;

import details.EngineEl;
import details.Time;
import details.Sex;
import entities.Entity;
import events.EventException;
import happened.Sound;

public class Engine extends Entity implements EngineFeatures {
    private boolean condition;
    private boolean jammed;
    private int speed;

    public final Gases gases = new Gases("нагретые газы");

    public Engine(String name) {
        super(name, Sex.THEY);
        condition = false;
        jammed = false;
        speed = 0;
    }

    public String turnOn(Time time) throws EventException {
        condition = true;
        if (jammed)
            throw new EventException("Ошибка поворота, заклинило двигатель.");

        speed = 100;
        return switch (time) {
            case PAST -> "включился";
            case FUTURE -> "включится";
            default -> "включается";
        };
    }

    public String turnOff(Time time) {
        condition = false;
        speed = 50;
        return switch (time) {
            case PAST -> "выключился";
            case FUTURE -> "выключится";
            default -> "выключается";
        };
    }

    public String sounds() {
        return new Sound() {
            public String sounds(String s) {
                return "послышалось" + s;
            }
        }.sounds("\"Чаф-чаф-чаф!\"");
    }

    public String getElement(EngineEl el) {
        return switch (el) {
            case NOZZLE -> "сопла";
            case FUEL -> "топливо";
            case EXHAUST -> gases.getName();
            default -> "элемент двигателя";
        };
    }

    public boolean getCondition() {
        return condition;
    }

    public boolean isJammed() {
        return jammed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
