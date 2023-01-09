package looker;

import details.Time;
import details.Size;
import details.Form;

public interface ItemDescription {
    String seems(Time time);

    String looks(Time time);

    String sizes(Size size);

    String forms(Form form);

    String formslike(Form form);
}