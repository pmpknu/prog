package pokemons;

import ru.ifmo.se.pokemon.Type;
import moves.physical.RockTomb;
import moves.physical.KarateChop;
import moves.physical.Bulldoze;
import moves.status.DoubleTeam;

/**
 * Sawk is a Fighting type Pokémon introduced in Generation 5.
 * It is known as the Karate Pokémon.
 */
public class Sawk extends ru.ifmo.se.pokemon.Pokemon {
    public Sawk() {
        this("Sawk", 1);
    }

    public Sawk(String name, int lvl) {
        super(name, lvl);
        this.setLevel(lvl);
        this.setType(Type.FIGHTING);
        this.setStats(75, 125, 75, 30, 75, 85);
        this.addMove(new RockTomb());
        this.addMove(new KarateChop());
        this.addMove(new Bulldoze());
        this.addMove(new DoubleTeam());
    }
}
