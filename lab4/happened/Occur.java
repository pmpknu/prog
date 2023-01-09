package happened;

import details.Time;

public interface Occur {
    public String occurs(Time time);

    public String occursInstantly(Time time);

    public String fasterThan();

    public String mightTell();
}
