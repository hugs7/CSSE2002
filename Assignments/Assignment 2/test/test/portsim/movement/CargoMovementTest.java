package portsim.movement;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.cargo.*;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CargoMovementTest {

    CargoMovement movement1;
    CargoMovement movement2;
    CargoMovement movement3;
    List<Cargo> cargo1;
    List<Cargo> cargo2;
    List<Cargo> cargo3;

    @Before
    public void setUp() throws Exception {
        Cargo.resetCargoRegistry();
        cargo1 = new ArrayList<>();
        cargo1.add(new BulkCargo(1,"ABC", 10,BulkCargoType.OTHER));
        Cargo.resetCargoRegistry();
        cargo2 = new ArrayList<>();
        cargo2.add(new Container(1,"ABC", ContainerType.OTHER));
        cargo2.add(new Container(2,"DEF", ContainerType.OPEN_TOP));
        Cargo.resetCargoRegistry();
        cargo3 = new ArrayList<>();
        cargo3.add(new BulkCargo(1,"ABC", 10,BulkCargoType.OTHER));
        cargo3.add(new Container(2,"ABC", ContainerType.OTHER));
        cargo3.add(new Container(3,"DEF", ContainerType.OPEN_TOP));
        movement1 = new CargoMovement(0,MovementDirection.INBOUND,cargo1);
        movement2 = new CargoMovement(0,MovementDirection.OUTBOUND,cargo2);
        movement3 = new CargoMovement(50,MovementDirection.INBOUND,cargo3);
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
    @Test
    public void fromString_Valid_Test() {
        String exceptionFail = "The given string should not throw a " +
                "BadEncodingException";
        String e1 = "CargoMovement:0:INBOUND:1:1";
        String e3 = "CargoMovement:50:INBOUND:3:1,2,3";

        CargoMovement c1 = null;
        CargoMovement c3 = null;

        try {
            c1 = CargoMovement.fromString(e1);
            c3 = CargoMovement.fromString(e3);

        } catch (BadEncodingException e) {
            fail(exceptionFail);
        }
        assertEquals(e1,c1.encode());
        assertEquals(e3,c3.encode());

    }
}