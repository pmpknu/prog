package pokemons;

import moves.status.Growth;
import ru.ifmo.se.pokemon.Type;

/**
 * Nuzleaf is a Grass/Dark type Pokémon introduced in Generation 3.
 * It is known as the Wily Pokémon.
 */
public class Nuzleaf extends Seedot {
    public Nuzleaf() {
        this("Nuzleaf", 1);
    }

    public Nuzleaf(String name, int lvl) {
        super(name, lvl);
        this.setLevel(lvl);
        this.setType(Type.GRASS, Type.DARK);
        this.setStats(70, 70, 40, 60, 40, 60);
        this.addMove(new Growth());
    }
}
