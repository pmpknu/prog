package entities.characters;

import java.util.Objects;
import details.*;
import entities.Entity;
import events.EventError;
import looker.Look;

public class Character extends Entity implements Look {
    boolean fainted;

    public Character(String name) {
        super(name);
        fainted = false;
    }

    public Character(String name, Sex sex) {
        super(name, sex);
        fainted = false;
    }

    public String toFind(Time time) {
        if (fainted)
            throw new EventError(name + "не может обнаружить, т.к. упал в обморок");

        return Action(time,
                "обнаружил", "обнаружила",
                "обнаружит", "ищет");
    }

    public String toLook(Time time) {
        if (fainted)
            throw new EventError(name + "не может смотреть, т.к. упал в обморок");

        return Action(time,
                "посмотрел", "посмотрела",
                "смотрит", "посмотрит");
    }

    public String wantToKnow(Time time) {
        if (fainted)
            throw new EventError(name + "не может хотеть, т.к. упал в обморок");

        return Action(time,
                "хотел узнать", "хотела узнать",
                "хочет узнать", "будет хотеть узнать");
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