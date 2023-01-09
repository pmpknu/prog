package moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Status;

/**
 * Facade deals damage, and hits with double power (140) if the user is burned,
 * poisoned or paralyzed.
 */
public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected double calcBaseDamage(Pokemon att, Pokemon def) {
        double result = super.calcBaseDamage(att, def);
        Status i = att.getCondition();

        if (i == Status.POISON | i == Status.BURN | i == Status.PARALYZE) {
            result *= 2;
        }

        return result;
    }

    @Override
    protected String describe() {
        return "использует Facade";
    }
}