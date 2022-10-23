package moves.physical;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;
import ru.ifmo.se.pokemon.Stat;

/**
 * Rock Tomb deals damage and lowers the target's Speed by one stage.
 * (Stats can be lowered to a minimum of -6 stages each.)
 */
public class AerialAce extends PhysicalMove {
    public AerialAce() {
        super(Type.FLYING, 60, (int) Double.POSITIVE_INFINITY);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        pokemon.setMod(Stat.SPEED, -1);
    }

    @Override
    protected String describe() {
        return "использует Aerial Ace";
    }
}
