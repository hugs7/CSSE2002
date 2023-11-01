package portsim.evaluators;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.cargo.Cargo;
import portsim.movement.CargoMovement;
import portsim.movement.Movement;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ShipThroughputEvaluatorTest {
    ShipThroughputEvaluator testShipEval;

    Movement testMovement0;
    Movement testMovement1;
    Movement testMovement2;
    Movement testMovement3;


    @Before
    public void setUp() throws Exception {
        testShipEval = new ShipThroughputEvaluator();

        Ship testShip0 = new BulkCarrier(3214568, "BulkCarrier 0",
                "UQ", NauticalFlag.BRAVO, 2800);
        Ship testShip1 = new BulkCarrier(4698512, "BulkCarrier 1",
                "UQ", NauticalFlag.NOVEMBER, 2800);
        Ship testShip2 = new ContainerShip(7654321, "Container Ship",
                "Brisbane", NauticalFlag.HOTEL, 2200);

        List<Cargo> testCargo = new ArrayList<>();

        Cargo testCargo0 = new BulkCargo(4, "Hawken", 1234, BulkCargoType.COAL);
        Cargo testCargo1 = new BulkCargo(5, "Hawken", 1234, BulkCargoType.GRAIN);
        Cargo testCargo2 = new BulkCargo(6, "Hawken", 1234, BulkCargoType.MINERALS);

        testCargo.add(testCargo0);
        testCargo.add(testCargo1);
        testCargo.add(testCargo2);


        testMovement0 = new ShipMovement(0, MovementDirection.OUTBOUND, testShip0);
        testMovement1 = new ShipMovement(1, MovementDirection.OUTBOUND, testShip1);
        testMovement2 = new ShipMovement(2, MovementDirection.INBOUND, testShip2);
        testMovement3 = new CargoMovement(3, MovementDirection.INBOUND, testCargo);
    }

    @Test
    public void testGetThroughputPerHour() {
        testShipEval.onProcessMovement(testMovement0);
        testShipEval.elapseOneMinute();
        testShipEval.onProcessMovement(testMovement1);
        testShipEval.elapseOneMinute();

        assertEquals(2, testShipEval.getThroughputPerHour());
    }

    @Test
    public void testInvalidMovement() {
        testShipEval.onProcessMovement(testMovement0);
        testShipEval.elapseOneMinute();
        testShipEval.onProcessMovement(testMovement1);
        testShipEval.elapseOneMinute();
        testShipEval.onProcessMovement(testMovement3);
        testShipEval.elapseOneMinute();

        assertEquals(2, testShipEval.getThroughputPerHour());
    }



    @After
    public void tearDown() throws Exception {
        testShipEval = null;
        Cargo.resetCargoRegistry();
        Ship.resetShipRegistry();
    }
}