package portsim.cargo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.TestUtil;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;
import portsim.util.NoSuchCargoException;

import java.util.*;

import static org.junit.Assert.*;

public class CargoTest {

    private DummyCargo dummyCargo1;
    private DummyCargo dummyCargo2;
    private DummyCargo dummyCargo3;

    private Container container;
    private BulkCargo bulkCargo;

    private Map<Integer,Cargo> registryCopy;

//
//

    private final Random random = new Random();

    /*
     * Dummy Cargo don't extend BulkCargo or Container, useful
     * for testing methods overridden in Cargo subclasses
     */
    static class DummyCargo extends Cargo {

        public DummyCargo(int id, String destination) {
            super(id, destination);
        }

    }
    @Before
    public void setUp() throws Exception {
        Cargo.resetCargoRegistry();
        registryCopy = new HashMap<>();

        dummyCargo1 = new DummyCargo(1,"Australia");
        dummyCargo2 = new DummyCargo(2,"Australia");
        dummyCargo3 = new DummyCargo(3,"New Zealand");

        registryCopy.put(dummyCargo1.getId(),dummyCargo1);
        registryCopy.put(dummyCargo2.getId(),dummyCargo2);
        registryCopy.put(dummyCargo3.getId(),dummyCargo3);

        container = new Container(4,"ABC",ContainerType.OTHER);
        bulkCargo = new BulkCargo(5,"DEF",50,BulkCargoType.OTHER);

        registryCopy.put(4,container);
        registryCopy.put(5,bulkCargo);
    }

    @After
    public void tearDown() throws Exception {
        Cargo.resetCargoRegistry();
        Ship.resetShipRegistry();
    }

    @Rule
    public Timeout timeout = Timeout.seconds(1);

//
    @Test
    public void constructorThrowsExceptionDuplicateID_Test(){
        try{
            Cargo cargo1 = new DummyCargo(23,"N/A");
            Cargo exception = new DummyCargo(23,"N/A");
            fail("Cargo constructor should throw an IllegalArgumentException if a" +
                    " duplicate id is given");
        } catch (IllegalArgumentException expected) {}
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
    public void encode_Test() {
        assertEquals("DummyCargo:1:Australia",
                dummyCargo1.encode());
        assertEquals("DummyCargo:3:New Zealand",
                dummyCargo3.encode());
    }
}