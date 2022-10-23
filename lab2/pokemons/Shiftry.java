package pokemons;

import moves.physical.AerialAce;
import ru.ifmo.se.pokemon.Type;

/**
 * Shiftry is a Grass/Dark type Pokémon introduced in Generation 3.
 * It is known as the Wicked Pokémon.
 */
public class Shiftry extends Nuzleaf {
    public Shiftry() {
        this("Shiftry", 1);
    }

    public Shiftry(String name, int lvl) {
        super(name, lvl);
        this.setLevel(lvl);
        this.setType(Type.GRASS, Type.DARK);
        this.setStats(90, 100, 60, 90, 60, 80);
        this.addMove(new AerialAce());
    }
}
