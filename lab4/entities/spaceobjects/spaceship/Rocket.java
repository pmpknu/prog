package entities.spaceobjects.spaceship;

import java.util.Objects;

import details.RocketEl;
import details.Sex;
import details.Time;
import entities.spaceobjects.spaceship.engine.Engine;
import entities.characters.Character;

public class Rocket extends SpaceShip {

    public final Engine mainEngine = new Engine("основной двигатель");
    public final Engine rotateEngine = new Engine("двигатель поворота");
    public final Cab cab = new Cab();

    public Rocket(String name, Sex sex, entities.characters.Character[] crew) {
        super(name, sex, crew);
    }

    public Rocket(String name, Sex sex, Character crew) {
        super(name, sex, crew);
    }

    public String noiseCondition() {
        return (mainEngine.getCondition() | rotateEngine.getCondition()) ? "громче" : "стало тихо";
    }

    public String rotate(Time time) {
        return Action(time, "повернулся", "повернулась", "поворачивается", "повернется");
    }

    public String howRotate(Time time) {
        return Action(time, "повернут", "повернута", "поворачивается", "повернется");
    }

    public String rotatedEl(RocketEl el) {
        return switch (el) {
            case BOW -> "носовой частью";
            case TAIL -> "хвостовой частью";
            default -> "боковой частью";
        };
    }

    public String startToStop() {
        return "начала замедлять ход";
    }

    public String speed() {
        if (mainEngine.getSpeed() < 100)
            return "небольшой скоростью";
        return "большой скоростью";
    }

    public String crash() {
        if (mainEngine.getSpeed() > 1000)
            return "разбилась";
        return "не разбилась";
    }

    public String overload() {
        return "начались перегрузки";
    }

    public class Cab {
        public String getName() {
            return "кабины";
        }

        public String pressedToFloor() {
            return crew.printMembers() + "прижались к полу кабины";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        Rocket r = (Rocket) o;
        return Objects.equals(name, r.name);
    }

    @Override
    public String toString() {
        return "Rocket{name='" + name + "'}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, this.toString());
    }
}
