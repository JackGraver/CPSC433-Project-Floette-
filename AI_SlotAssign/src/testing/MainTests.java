package testing;
import assignments.NotCompatible;
import assignments.Partial;
import assignments.Unwanted;
import enums.Days;
import enums.SlotType;
import main.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import scheduling.Activity;
import scheduling.Game;
import scheduling.Slot;
import utility.Data;

import static org.junit.jupiter.api.Assertions.*;

public class MainTests {
    private Data data;

    @BeforeEach
    void setup() {
        data = new Data("Test", 0, 1, 0, 1, 0, 1, 0 , 1);
        Main.data = data;

        Activity game1 = new Game("Game1");
        Activity game2 = new Game("Game2");
        Activity prac1 = new Game("Prac1");
        Activity prac2 = new Game("Prac2");

        Slot slot = new Slot(SlotType.Game, Days.MO, "8:00", 3, 2);

        data.addActivity(game1);
        data.addActivity(game2);
        data.addActivity(prac1);
        data.addActivity(prac2);

        data.addSlot(slot);

        data.addPartial(new Partial(game1, slot));

        data.addCompatible(new NotCompatible(game1, game2));

        data.addUnwanted(new Unwanted(game1, slot));
    }

    @Test
    public void testPartAssign() {
        Main.data = data;
        Activity res = Main.f_trans();

        System.out.println("pa: " + res);

        assertEquals("Game1", res.getIdentifier());
    }

    @Test
    public void testAssociatedActivity() {

    }

    @Test
    public void testNotCompatible() {
        Main.f_trans();

        Activity res = Main.f_trans();
        System.out.println("nc: " + res);

        assertEquals("Game1", res.getIdentifier());
    }

    @Test
    public void testUnwanted() {
        Main.f_trans();
        Main.f_trans();

        Activity res = Main.f_trans();
        System.out.println("un: " + res);

        assertEquals("Game1", res.getIdentifier());
    }

    @Test
    public void testOtherwise() {
        Main.f_trans();
        Main.f_trans();
        Main.f_trans();

        Activity res = Main.f_trans();
        System.out.println("o: " + res);

        assertEquals("Game2", res.getIdentifier());
    }
}
