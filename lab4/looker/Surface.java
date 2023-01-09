package looker;

import details.Time;

public interface Surface {
    String seemsLike(Time time);

    String moveCloser(Time time);

    String moveAway(Time time);
}