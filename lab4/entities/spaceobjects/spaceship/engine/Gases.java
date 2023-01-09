package entities.spaceobjects.spaceship.engine;

import details.Direction;
import details.Sex;
import entities.spaceobjects.SpaceObject;
import looker.ItemMovementDescription;

public class Gases extends SpaceObject implements ItemMovementDescription {

    public Gases(String name) {
        super(name, Sex.THEY);
    }

    public String released() {
        return "выбрасывались";
    }

    public String direction() {
        return "в направлении";
    }

    public String whatDirection(Direction d) {
        return switch (d) {
            case FORWARD -> "движения";
            case BACKWARD -> "противоположном движению";
            default -> "в сторону";
        };
    }
}
