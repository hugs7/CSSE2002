package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.TestUtil;
import portsim.cargo.Cargo;
import portsim.ship.*;
import portsim.util.BadEncodingException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ShipQueueTest {
//
//
    private ShipQueue shipQueue;
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;

    @Rule
    public Timeout timeout = Timeout.seconds(3);

    @Before
    public void setUp() {
        shipQueue = new ShipQueue();
        ship1 = new BulkCarrier(1234567, "Victory", "United Kingdom",
                NauticalFlag.NOVEMBER, 400);
        ship2 = new BulkCarrier(3456789, "Glorious", "Switzerland",
                NauticalFlag.HOTEL, 120);
        ship3 = new ContainerShip(2545679, "Legion", "France",
                NauticalFlag.BRAVO, 800);
    }

    @After
    public void tearDown() {
        Cargo.resetCargoRegistry();
        Ship.resetShipRegistry();
    }

//
//
//
//
//
//
    @Test
    public void pollSingleShipTest() {
        shipQueue.add(ship1);
        assertEquals(ship1, shipQueue.poll());
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
    @Test
    public void peekOrderTest1() {
        shipQueue.add(ship2); // ready to be docked
        shipQueue.add(ship3); // carrying dangerous cargo, should be removed first

        assertEquals(ship3, shipQueue.peek());
    }
}