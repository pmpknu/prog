package entities;

import java.util.Objects;

import details.Sex;
import details.Time;

public abstract class Entity {
    protected String name;
    protected Sex sex;

    public Entity(String name) {
        this.name = name;
        this.sex = Sex.MALE;
    }

    public Entity(String name, Sex sex) {
        this.name = name;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    protected String Action(Time time,
            String pastMale, String presentMale, String futureMale,
            String pastFemale, String presentFemale, String futureFemale) {
        String verb = switch (time) {
            case PAST ->
                switch (this.sex) {
                    case MALE -> pastMale;
                    case FEMALE -> pastFemale;
                };
            case PRESENT ->
                switch (this.sex) {
                    case MALE -> presentMale;
                    case FEMALE -> presentFemale;
                };
            case FUTURE ->
                switch (this.sex) {
                    case MALE -> futureMale;
                    case FEMALE -> futureFemale;
                };
            default -> presentMale;
        };
        return verb;
    }

    protected String Action(Time time,
            String pastMale, String pastFemale, String present, String future) {
        String verb = switch (time) {
            case PAST ->
                switch (this.sex) {
                    case MALE -> pastMale;
                    case FEMALE -> pastFemale;
                };
            case FUTURE -> future;
            default -> present;
        };
        return verb;
    }

    protected String Action(Time time,
            String past, String present, String future) {
        String verb = switch (time) {
            case PAST -> past;
            case FUTURE -> future;
            default -> present;
        };
        return verb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Entity e = (Entity) o;
        return this.name == e.name;
    }

    @Override
    public String toString() {
        String sex = switch (this.sex) {
            case FEMALE -> "female";
            case MALE -> "male";
            default -> "they";
        };

        return "Entity{name='" + name + "', '" + sex + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, sex, this.toString());
    }
}
