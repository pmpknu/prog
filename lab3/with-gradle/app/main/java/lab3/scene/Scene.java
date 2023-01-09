package scene;

import entities.characters.Character;
import entities.spaceobjects.Earth;
import entities.spaceobjects.Sun;
import entities.spaceobjects.moon.Moon;
import entities.spaceobjects.moon.Stone;
import looker.Porthole;
import details.*;

/*
 * Пончик посмотрел в иллюминатор и обнаружил, что лунная поверхность вовсе не
 * удалялась, а приближалась.
 * Теперь она уже не казалась пепельно-серой, какой кажется нам с земли, а была
 * серебристо-белой.
 * В разные стороны тянулись красивые горы, между которыми сверкали, залитые
 * ярким солнечным светом, лунные долины.
 * Среди долин во многих местах виднелись огромные каменные глыбы.
 * Некоторые из них были четырехугольной формы и своим видом напоминали большие
 * дома.
 * Особенно много таких камней было у подножья скалистых гор, поэтому казалось,
 * что вдоль горных хребтов расположились лунные города, населенные лунными
 * жителями.
 */

public class Scene {
    public void build() {
        Character ponchik = new Character("Пончик", Sex.MALE);
        Porthole porthole = new Porthole("иллюминатор", Material.GLASS);
        Moon moon = new Moon("Луна");
        Earth earth = new Earth("Земля");
        Sun sun = new Sun("Солнце");
        Stone stones = new Stone("каменные глыбы", Quantity.MANY);

        System.out.printf("%s %s в %s и %s, что %s не %s, а %s.\n",
                ponchik.getName(), ponchik.toLook(Time.PAST), porthole.getName(), ponchik.toFind(Time.PAST),
                moon.getSurface(), moon.moveAway(Time.PAST), moon.moveCloser(Time.PAST));
        System.out.printf("%s не %s %s, какой %s %s, а %s %s.\n",
                moon.getSurface(), moon.seemsLike(Time.PAST), moon.getSurfaceColor(SurfaceCol.GREY),
                moon.seemsLike(Time.PRESENT), earth.from(), moon.seemsLike(Time.PAST),
                moon.getSurfaceColor(SurfaceCol.WHITE));
        System.out.printf("%s %s, между которыми %s, %s %s %s, %s.\n",
                moon.whatDoOnSurface(Landscape.MOUNTAINS), moon.whatIsOnSurface(Landscape.MOUNTAINS),
                moon.whatDoOnSurface(Landscape.VALLEY), sun.bathedIn(Quantity.MANY),
                sun.luminance(Quantity.MANY, Luminance.BRIGHT),
                sun.shining(), moon.whatIsOnSurface(Landscape.VALLEY));
        System.out.printf("%s %s %s.\n",
                stones.seems(Time.PAST), stones.sizes(Size.LARGE), stones.getName());
        System.out.printf("%s %s %s как %s %s.\n",
                stones.getName(), stones.forms(Form.QUADRO), stones.looks(Time.PAST),
                stones.sizes(Size.BIG), stones.formslike(Form.QUADRO));
        System.out.printf("%s %s было у %s, поэтому %s, что %s %s %s %s.\n",
                stones.alotof(), stones.named(), moon.whatIsOnSurface(Landscape.FOOT), moon.seemsLike(Time.PAST),
                moon.whatDoOnSurface(Landscape.RIDGE), moon.whatIsOnSurface(Landscape.RIDGE),
                moon.whatDoOnSurface(Landscape.TOWN), moon.whatIsOnSurface(Landscape.TOWN));
    }
}
