package moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

/**
 * Karate Chop deals damage and has an increased critical hit ratio (1/8 instead
 * of 1/24).
 */
public class KarateChop extends PhysicalMove {
    public KarateChop() {
        super(Type.FIGHTING, 50, 100);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        return Math.random() < 0.125 ? 2 : 1;
    }

    @Override
    protected String describe() {
        return "использует Karate Chop";
    }
}