package entities.spaceobjects;

import details.Sex;

public class Earth extends SpaceObject {
    public Earth(String name) {
        super(name, Sex.FEMALE);
    }

    public String from() {
        return "с Земли";
    }
}