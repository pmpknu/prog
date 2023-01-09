package scene;

import entities.characters.Character;
import entities.spaceobjects.Earth;
import entities.spaceobjects.Sun;
import entities.spaceobjects.moon.Moon;
import entities.spaceobjects.moon.Stone;
import entities.spaceobjects.spaceship.Rocket;
import entities.these.This;
import events.EventException;
import looker.Porthole;
import details.*;

/*
 * Все это произошло мгновенно.
 * Гораздо быстрей, чем об этом можно рассказать.
 * Когда ракета повернулась хвостовой частью к Луне, двигатель поворота выключился.
 * На минуточку стало тихо. Но вскоре снова послышалось: "Чаф-чаф-чаф!" На этот раз громче обычного.
 * Это включился основной двигатель. Но так как теперь ракета была обращена хвостовой частью к Луне,
 * нагретые газы выбрасывались из сопла в направлении, противоположном движению,
 * благодаря чему ракета начала замедлять ход.
 * Это было необходимо для того, чтобы ракета приблизилась к Луне с небольшой скоростью и не разбилась при посадке.
 * Как только ракета замедлила ход, начались перегрузки, 
 * и возникшая сила тяжести прижала Незнайку и Пончика к полу кабины.
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
        public void build() throws EventException {
                Character ponchik = new Character("Пончик", Sex.MALE);
                Character dunno = new Character("Незнайка", Sex.MALE);
                Porthole porthole = new Porthole("иллюминатор", Material.GLASS);
                Moon moon = new Moon("Луна");
                Earth earth = new Earth("Земля");
                Sun sun = new Sun("Солнце");
                Stone stones = new Stone("каменные глыбы", Quantity.MANY);
                This thisis = new This("это");
                Character[] crew = { ponchik, dunno };
                Rocket rocket = new Rocket("ракета", Sex.FEMALE, crew);

                System.out.printf("%s %s.\n",
                                thisis.allOfThis(LetterSize.CAPITAL), thisis.occursInstantly(Time.PAST));
                System.out.printf("%s, чем %s.\n",
                                thisis.fasterThan(), thisis.mightTell());
                System.out.printf("Когда %s %s %s к %s, %s %s.\n",
                                rocket.getName(), rocket.rotate(Time.PAST),
                                rocket.rotatedEl(RocketEl.TAIL), moon.toWhatName(),
                                rocket.rotateEngine.getName(), rocket.rotateEngine.turnOff(Time.PAST));
                System.out.printf("%s. ",
                                rocket.noiseCondition());
                String mainOn = rocket.mainEngine.turnOn(Time.PAST);
                System.out.printf("Но вскоре %s %s.\n",
                                rocket.mainEngine.sounds(), rocket.noiseCondition());
                System.out.printf("Это %s %s.\n", mainOn, rocket.mainEngine.getName());
                System.out.printf("Но %s была %s %s %s к %s, %s %s из %s в %s %s, поэтому %s %s.\n",
                                rocket.getName(), rocket.howRotate(Time.PAST),
                                rocket.rotate(Time.PAST), rocket.rotatedEl(RocketEl.TAIL),
                                moon.toWhatName(), rocket.mainEngine.getElement(EngineEl.EXHAUST),
                                rocket.mainEngine.gases.released(), rocket.mainEngine.getElement(EngineEl.NOZZLE),
                                rocket.mainEngine.gases.direction(),
                                rocket.mainEngine.gases.whatDirection(Direction.BACKWARD),
                                rocket.getName(), rocket.startToStop());
                rocket.mainEngine.setSpeed(50);
                System.out.printf("Чтобы %s %s к %s %s и %s о %s.\n",
                                rocket.getName(), rocket.moveCloser(Time.PAST),
                                moon.toWhatName(), rocket.speed(),
                                rocket.crash(), moon.getSurface());
                System.out.printf("%s и %s.\n",
                                rocket.overload(), rocket.cab.pressedToFloor());
                System.out.printf("%s %s что %s c %s.\n",
                                dunno.getName(), dunno.wantToKnow(Time.PAST), thisis.occurs(Time.PAST), moon.getName());
                // третья лаба
                System.out.printf("%s %s в %s и %s, что %s не %s, а %s.\n",
                                ponchik.getName(), ponchik.toLook(Time.PAST), porthole.getName(),
                                ponchik.toFind(Time.PAST),
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
                                stones.alotof(), stones.named(), moon.whatIsOnSurface(Landscape.FOOT),
                                moon.seemsLike(Time.PAST),
                                moon.whatDoOnSurface(Landscape.RIDGE), moon.whatIsOnSurface(Landscape.RIDGE),
                                moon.whatDoOnSurface(Landscape.TOWN), moon.whatIsOnSurface(Landscape.TOWN));
        }
}
