package pokemons;

import ru.ifmo.se.pokemon.Type;
import moves.special.EnergyBall;
import moves.physical.Facade;

/**
 * Seedot is a Grass type Pokémon introduced in Generation 3.
 * It is known as the Acorn Pokémon.
 */
public class Seedot extends ru.ifmo.se.pokemon.Pokemon {
    public Seedot() {
        this("Seedot", 1);
    }

    public Seedot(String name, int lvl) {
        super(name, lvl);
        this.setLevel(lvl);
        this.setType(Type.GRASS);
        this.setStats(40, 40, 50, 30, 30, 30);
        this.addMove(new EnergyBall());
        this.addMove(new Facade());
    }
}