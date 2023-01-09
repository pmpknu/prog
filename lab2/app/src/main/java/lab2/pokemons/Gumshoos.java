package pokemons;

import ru.ifmo.se.pokemon.Type;
import moves.physical.Bulldoze;

/**
 * Gumshoos is a Normal type Pokémon introduced in Generation 7.
 * It is known as the Stakeout Pokémon.
 * 
 * Gumshoos' method of targeting prey is the exact opposite of Yungoos's
 * strategy. While Yungoos prowls around, Gumshoos stakes out its prey's usual
 * routes and waits patiently for it to come by.
 * 
 * Gumshoos has a tenacious personality, which is why it targets one prey for so
 * long without wavering. But when the sun goes down, it runs low on stamina,
 * falling asleep right on the spot. Gumshoos can withstand a great deal of
 * hunger. It's able to stay perfectly still while waiting for its prey, keeping
 * watch without eating a thing.
 * 
 * Gumshoos evolves from Yungoos.
 */
public class Gumshoos extends Yungoos {
    public Gumshoos() {
        this("Gumshoos", 1);
    }

    public Gumshoos(String name, int lvl) {
        super(name, lvl);
        this.setLevel(lvl);
        this.setType(Type.NORMAL);
        this.setStats(88, 110, 60, 55, 60, 45);
        this.addMove(new Bulldoze());

    }
}
