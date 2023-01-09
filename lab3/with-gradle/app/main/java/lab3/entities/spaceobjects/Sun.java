package entities.spaceobjects;

import details.Luminance;
import details.Quantity;
import details.Sex;

public class Sun extends SpaceObject {
    public Sun(String name) {
        super(name, Sex.MALE);
    }

    public String bathedIn(Quantity q) {
        String phrase = switch (q) {
            case MANY -> "залитые";
            default -> "залитый";
        };
        return phrase;
    }

    public String luminance(Quantity q, Luminance lumi) {
        return switch (q) {
            case MANY ->
                switch (lumi) {
                    case BRIGHT -> "ярким";
                    case DIM -> "тусклым";
                    default -> "обычным";
                };
            default ->
                switch (lumi) {
                    case BRIGHT -> "яркиЙ";
                    case DIM -> "тусклыЙ";
                    default -> "обычныЙ";
                };
        };
    }

    public String shining() {
        return "солнечным светом";
    }

}