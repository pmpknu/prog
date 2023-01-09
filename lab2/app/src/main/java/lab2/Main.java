import pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

public class Main {
    public static void  main(String[] args) {
        Battle b = new Battle();
        Pokemon p1 = new Sawk("Karatist", 5);
        Pokemon p2 = new Yungoos("Vudra", 33);
        Pokemon p3 = new Gumshoos();
        Pokemon p4 = new Shiftry();
        Pokemon p5 = new Nuzleaf("Oreshki", 21);
        Pokemon p6 = new Seedot("Dub", 54);

        b.addAlly(p1);
        b.addFoe(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addAlly(p5);
        b.addFoe(p6);
        b.go();
    }
}