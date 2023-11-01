package portsim.evaluators;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.cargo.*;
import portsim.movement.CargoMovement;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ShipFlagEvaluatorTest {

    Ship ship1;
    Ship ship2;
    Ship ship3;

    ShipMovement movement1;
    ShipMovement movement2;
    ShipMovement movement3;

    ShipFlagEvaluator e;

    @Before
    public void setUp() throws Exception {
        e = new ShipFlagEvaluator();
        Ship.resetShipRegistry();
        ship1 = new BulkCarrier(3456789, "Glorious", "ABC",
                NauticalFlag.HOTEL, 120);
        ship2 = new BulkCarrier(3456788, "Glorious", "ABC",
                NauticalFlag.HOTEL, 120);
        ship3 = new ContainerShip(1234567, "Polly", "DEF",
                NauticalFlag.WHISKEY, 4);

        movement1 = new ShipMovement(0,
                MovementDirection.INBOUND,ship1);
        movement2 = new ShipMovement(0,
                MovementDirection.INBOUND,ship2);
        movement3 = new ShipMovement(0,
                MovementDirection.INBOUND,ship3);
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
    @Test
    public void getFlagStatistics_Test(){
        assertEquals(0,e.getFlagStatistics("ABC"));
        e.onProcessMovement(movement1);
        assertEquals(1,e.getFlagStatistics("ABC"));
        e.onProcessMovement(movement2);
        assertEquals(2,e.getFlagStatistics("ABC"));
        e.onProcessMovement(movement3);
        assertEquals(2,e.getFlagStatistics("ABC"));
        assertEquals(1,e.getFlagStatistics("DEF"));

    }


}