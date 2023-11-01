package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.TestUtil;
import portsim.cargo.Cargo;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import java.util.Random;

import static org.junit.Assert.*;

public class BulkQuayTest {

    BulkQuay quay1;
    BulkQuay quay2;
    Ship containerShip;

    private final Random random = new Random();
    private static final String equalsTrapMsg = "BulkQuay.equals() should not" +
            " always return true. Check your implementation";

    @Before
    public void setUp() throws Exception {
        Ship.resetShipRegistry();
        containerShip = new ContainerShip(1234567,"ABC","DEF",
                NauticalFlag.BRAVO,50);
        quay1 = new BulkQuay(1,200);
        quay2 = new BulkQuay(2,250);
    }

    @After
    public void tearDown() throws Exception {
        Cargo.resetCargoRegistry();
        Ship.resetShipRegistry();
    }
    @Rule
    public Timeout timeout = Timeout.seconds(1);

//
//
//
//
//
//
//
//
//
//
//
//
    @Test
    public void equals_HandlesWrongType_Test() {
        TestUtil.assertEqualsOverridden(BulkQuay.class);

        try {
            assertNotEquals(equalsTrapMsg, quay1, "abcd");
        } catch (ClassCastException e) {
            fail("equals() should not fail for a parameter that is not an " +
                    "instance of BulkQuay");
        }
    }
}