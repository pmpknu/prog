package entities.characters;

import java.util.Objects;
import details.*;
import entities.Entity;
import looker.Look;

public class Character extends Entity implements Look {
    public Character(String name) {
        super(name);
    }

    public Character(String name, Sex sex) {
        super(name, sex);
    }

    public String toFind(Time time) {
        return Action(time,
                "обнаружил", "обнаружила",
                "обнаружит", "ищет");
    }

    public String toLook(Time time) {
        return Action(time,
                "посмотрел", "посмотрела",
                "смотрит", "посмотрит");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Character ch = (Character) o;
        return Objects.equals(name, ch.name);
    }

    @Override
    public String toString() {
        String sex = switch (this.sex) {
            case FEMALE -> "female";
            case MALE -> "male";
            default -> "they";
        };

        return "Character{name='" + name + "', '" + sex + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, sex, this.toString());
    }
}