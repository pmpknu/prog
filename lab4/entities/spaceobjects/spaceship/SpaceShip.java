package entities.spaceobjects.spaceship;

import java.util.Objects;
import java.util.ArrayList;

import details.Sex;
import entities.spaceobjects.SpaceObject;
import entities.characters.Character;

public abstract class SpaceShip extends SpaceObject {
    public static class Crew {
        private ArrayList<Character> CrewMembers = new ArrayList<>();

        public Crew(Character crew) {
            AddMember(crew);
        }

        public Crew(Character[] chs) {
            AddMember(chs);
        }

        protected void AddMember(Character ch) {
            CrewMembers.add(ch);
        }

        protected void AddMember(Character[] chs) {
            int len = chs.length;

            for (int i = 0; i < len; i++)
                CrewMembers.add(chs[i]);
        }

        protected void AddMember(ArrayList<Character> chs) {
            CrewMembers.addAll(chs);
        }

        public int getSize() {
            return CrewMembers.size();
        }

        public ArrayList<Character> getCrew() {
            return CrewMembers;
        }

        public Character getMember(int id) {
            if (id >= CrewMembers.size()) {
                throw new ArrayIndexOutOfBoundsException("Non - existent id");
            }
            return CrewMembers.get(id);
        }

        public String printMembers() {
            int len = CrewMembers.size();
            String s = "";

            for (int i = 0; i < len; i++) {
                if (i != len - 1)
                    s += CrewMembers.get(i).getName() + "и ";
                else
                    s += CrewMembers.get(i).getName();
            }
            return s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            if (!super.equals(o))
                return false;

            Crew c = (Crew) o;
            return Objects.equals(CrewMembers, c.CrewMembers);
        }

        @Override
        public String toString() {
            return "Crew{CrewMembers=" + CrewMembers.toString() + "}";
        }

        @Override
        public int hashCode() {
            return Objects.hash(super.hashCode(), this.toString());
        }

    }

    protected static Crew crew;

    public SpaceShip(String name, Sex sex, Character[] crew) {
        super(name, sex);
        SpaceShip.crew = new Crew(crew);
    }

    public SpaceShip(String name, Sex sex, Character crew) {
        super(name, sex);
        SpaceShip.crew = new Crew(crew);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;

        SpaceShip r = (SpaceShip) o;
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
