package looker;

import details.Direction;

public interface ItemMovementDescription {
    String released();

    String direction();

    String whatDirection(Direction d);
}