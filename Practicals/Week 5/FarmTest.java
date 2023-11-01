import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmTest {
    Farm defaultFarm, emptyFarm, customFarm;

    @Before
    public void setUp() throws Exception {
        defaultFarm = new Farm();
        emptyFarm = new Farm(new String[0], new String[0], new String[0], new int[0]);
        customFarm = new Farm(new String[]{"a"}, new String[]{"b"}, new String[]{"c"}, new int[]{1,1});
    }

    @Test
    public void greetChickens() {
        Assert.assertTrue("".equals(emptyFarm.greetChickens()));
    }

    @Test
    public void patCows() {
        Assert.assertFalse("".equals(defaultFarm.patCows()));
    }

    @Test
    public void countSheep() {
        try {
            emptyFarm.countSheep();
            // never should reach here
            Assert.fail();
        } catch (NoSheepException e) {

        }
    }

    @Test
    public void countSheepTwo() throws NoSheepException {
        Assert.assertTrue("c 1 sheep\n".equals(customFarm.countSheep()));
    }

    @Test
    public void harvest() {
        Assert.assertEquals(10, defaultFarm.harvest());
        Assert.assertEquals(0, defaultFarm.harvest());
    }
}