package entities.spaceobjects.moon;

import details.Quantity;
import details.Sex;
import details.Time;
import details.Size;
import details.Form;
import entities.spaceobjects.SpaceObject;
import looker.ItemDescription;

public class Stone extends SpaceObject implements ItemDescription {
    private Quantity q;

    public Stone(String name, Quantity q) {
        super(name, Sex.MALE);
        this.q = q;
    }

    public String alotof() {
        return "множество";
    }

    public String named() {
        return switch (q) {
            case ONE -> "камень";
            default -> "камней";
        };
    }

    public String seems(Time time) {
        String verb = switch (q) {
            case ONE -> Action(time, "виднелcя", "видется", "будет видется");
            default -> Action(time, "виднелись", "видятся", "будут видятся");
        };
        return verb;
    }

    public String looks(Time time) {
        String verb = switch (q) {
            case ONE -> Action(time, "выглядел", "выглядит", "будет выглядеть");
            default -> Action(time, "выглядели", "выглядят", "будут выглядить");
        };
        return verb;
    }

    public String sizes(Size size) {
        String word = switch (q) {
            case ONE ->
                switch (size) {
                    case SMALL -> "маленький";
                    case BIG -> "большой";
                    case LARGE -> "огромный";
                    default -> "средний"; // also MEDIUM
                };
            default ->
                switch (size) {
                    case SMALL -> "маленькие";
                    case BIG -> "большие";
                    case LARGE -> "огромные";
                    default -> "средние"; // also MEDIUM
                };
        };

        return word;
    }

    public String forms(Form form) {
        return switch (form) {
            case LINE -> "линейной формы";
            case CIRCLE -> "круглой формы";
            case QUADRO -> "четырехугольной формы";
            default -> "странной формы";
        };
    }

    public String formslike(Form form) {
        return switch (form) {
            case LINE -> "карандаш";
            case CIRCLE -> "тарелка";
            case QUADRO -> "дома";
            default -> "краказябра";
        };
    }

}
