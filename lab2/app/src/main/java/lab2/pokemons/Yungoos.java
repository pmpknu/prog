package pokemons;

import ru.ifmo.se.pokemon.Type;
import moves.physical.Tackle;
import moves.status.Leer;
import moves.physical.Facade;

/**
 * Yungoos is a Normal type Pokémon introduced in Generation 7.
 * It is known as the Loitering Pokémon.
 * 
 * Yungoos is a big eater that is never satisfied. The majority of its long body
 * is given over to its stomach, and its digestion is swift, so it’s always
 * hungry. It has strong fangs, so it can crush and consume the hardest of
 * objects.
 */
public class Yungoos extends ru.ifmo.se.pokemon.Pokemon {
    public Yungoos() {
        this("Yungoos", 1);
    }

    public Yungoos(String name, int lvl) {
        super(name, lvl);
        this.setLevel(lvl);
        this.setType(Type.NORMAL);
        this.setStats(48, 70, 30, 30, 30, 45);
        this.addMove(new Tackle());
        this.addMove(new Leer());
        this.addMove(new Facade());
    }
}
