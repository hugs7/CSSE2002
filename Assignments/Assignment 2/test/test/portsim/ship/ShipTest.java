package portsim.ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.TestUtil;
import portsim.cargo.*;
import portsim.port.Quay;
import portsim.util.BadEncodingException;
import portsim.util.NoSuchShipException;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class ShipTest {

    private Ship dummyShip1;
    private Ship dummyShip2;
    private Ship dummyShip3;

    private Map<Long,Ship> registryCopy;

    private static final String equalsTrapMsg = "Ship.equals() should not " +
            "always return true. Check your implementation";

    private final Random random = new Random();

    class DummyShip extends Ship {

        public DummyShip(long imoNumber, String name, String originFlag,
                         NauticalFlag flag) {
            super(imoNumber, name, originFlag, flag);
        }

        @Override
        public boolean canDock(Quay quay) {
            return false;
        }

        @Override
        public boolean canLoad(Cargo cargo) {
            return false;
        }

        @Override
        public void loadCargo(Cargo cargo) {

        }
    }

    @Before
    public void setUp() throws Exception {
        Ship.resetShipRegistry();
        registryCopy = new HashMap<>();

        this.dummyShip1 = new DummyShip(2345678, "Apple", "Italy", NauticalFlag.WHISKEY);
        this.dummyShip2 = new DummyShip(2545679, "Legion", "France", NauticalFlag.BRAVO);
        this.dummyShip3 = new DummyShip(1234567, "Orange", "Germany", NauticalFlag.HOTEL);

        registryCopy.put(dummyShip1.getImoNumber(),dummyShip1);
        registryCopy.put(dummyShip2.getImoNumber(),dummyShip2);
        registryCopy.put(dummyShip3.getImoNumber(),dummyShip3);
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
//
//
//
//
    @Test
    public void getShipByImoNumberException() {
        try {
            Ship.getShipByImoNumber(2345673);
            fail("getShipByImoNumber should throw an exception if the " +
                    "specified ship does not exist");
        } catch (NoSuchShipException e) {}
    }

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
    public void equals_HandlesNullTest() {
        TestUtil.assertEqualsOverridden(Ship.class);

        try {
            assertNotEquals(equalsTrapMsg, dummyShip2, null);
        } catch (NullPointerException e) {
            fail();
        }
    }

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
//
//
//
//
    @Test
    public void getShipRegistry_Test() {
        String failMsg = "getShipRegistry should return the ship registry";
        assertEquals(failMsg, registryCopy, DummyShip.getShipRegistry());
    }

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
//
//
//
//
//
//
    @Test
    public void fromStringWithInappropriateCargo() {
        String exceptionFail = "The given string should not throw a " +
                "BadEncodingException";

        String containerShipEncoding = "ContainerShip:2386559:Sally:England" +
                ":WHISKEY:3:1:13"; // trap, id 13 is bulk cargo

        String bulkCarrierEncoding = "BulkCarrier:2386557:Bob:France" +
                ":BRAVO:12:21"; // trap, id 21 is container

        BulkCargo bCargo = new BulkCargo(13, "France", 5, BulkCargoType.GRAIN);
        Container cContainer = new Container(21, "Germany",
                ContainerType.REEFER);

        Ship bulkCarrier1;
        Ship container1;

        try {
            container1 = Ship.fromString(containerShipEncoding);
            fail(exceptionFail);
        } catch (BadEncodingException e) {

        }

        try {
            bulkCarrier1 = Ship.fromString(bulkCarrierEncoding);
            fail(exceptionFail);
        } catch (BadEncodingException e) {

        }
    }
}