package entities.spaceobjects.moon;

import details.Landscape;
import details.Sex;
import details.SurfaceCol;
import entities.spaceobjects.SpaceObject;

public class Moon extends SpaceObject {
    public Moon(String name) {
        super(name, Sex.FEMALE);
    }

    public String getSurface() {
        return "лунная поверхность";
    }

    public String toWhatName() {
        return "Луне";
    }

    public String getSurfaceColor(SurfaceCol color) {
        String verb = switch (color) {
            case GREY -> "пепельно-серой";
            case WHITE -> "серебристо-белой";
            default -> "серо-буро-малиновый в крапинку";
        };
        return verb;
    }

    public String whatIsOnSurface(Landscape landscape) {
        String verb = switch (landscape) {
            case MOUNTAINS -> "красивые горы";
            case VALLEY -> "лунные долины";
            case FOOT -> "подножья скалистых гор";
            case RIDGE -> "горных хребтов";
            case TOWN -> "лунные города c жителями";
            default -> "что-то неопределенное";
        };
        return verb;
    }

    public String whatDoOnSurface(Landscape landscape) {
        String verb = switch (landscape) {
            case MOUNTAINS -> "тянулись";
            case VALLEY -> "сверкали";
            case RIDGE -> "вдоль";
            case TOWN -> "расположились";
            default -> "делали";
        };
        return verb;
    }
}