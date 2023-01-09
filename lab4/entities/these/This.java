package entities.these;

import java.util.Objects;

import details.LetterSize;
import details.Time;
import entities.Entity;
import happened.Occur;

public class This extends Entity implements Occur {
    public This(String name) {
        super(name);
    }

    public String allOfThis(LetterSize s) {
        return switch (s) {
            case CAPITAL -> "Все " + name;
            default -> "все " + name;
        };
    }

    public String occurs(Time time) {
        return switch (time) {
            case PAST -> "произошло";
            case FUTURE -> "произойдет";
            default -> "происходит";
        };
    }

    public String occursInstantly(Time time) {
        return switch (time) {
            case PAST -> "произошло мнгновенно";
            case FUTURE -> "произойдет мнгновенно";
            default -> "происходит мнгновенно";
        };
    }

    public String fasterThan() {
        return "гораздо быстрее";
    }

    public String mightTell() {
        return "можно рассказать";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        This t = (This) o;
        return Objects.equals(name, t.name);
    }

    @Override
    public String toString() {
        String sex = switch (this.sex) {
            case FEMALE -> "female";
            case MALE -> "male";
            default -> "they";
        };

        return "This{name='" + name + "', '" + sex + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, sex, this.toString());
    }
}