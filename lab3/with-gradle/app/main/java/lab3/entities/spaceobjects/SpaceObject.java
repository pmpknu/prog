package entities.spaceobjects;

import java.util.Objects;

import looker.Surface;
import details.Sex;
import details.Time;
import entities.Entity;

public abstract class SpaceObject extends Entity implements Surface {
    public SpaceObject(String name) {
        super(name);
    }

    public SpaceObject(String name, Sex sex) {
        super(name, sex);
    }

    public String seemsLike(Time time) {
        return Action(time,
                "казался", "казалась",
                "кажется", "покажется");
    }

    public String moveCloser(Time time) {
        return Action(time,
                "приближался", "приближалась",
                "приближается", "приблизиться");
    }

    public String moveAway(Time time) {
        return Action(time,
                "удалялся", "удалялась",
                "удаляется", "удалиться");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        SpaceObject s = (SpaceObject) o;
        return Objects.equals(name, s.name);
    }

    @Override
    public String toString() {
        String sex = switch (this.sex) {
            case FEMALE -> "female";
            case MALE -> "male";
            default -> "they";
        };

        return "SpaceObject{name='" + name + "', '" + sex + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, sex, this.toString());
    }
}