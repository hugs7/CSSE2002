package portsim.evaluators;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import portsim.cargo.Cargo;
import portsim.movement.MovementDirection;
import portsim.movement.ShipMovement;
import portsim.port.BulkQuay;
import portsim.port.Port;
import portsim.port.Quay;
import portsim.ship.BulkCarrier;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;

import static org.junit.Assert.*;

public class QuayOccupancyEvaluatorTest {

    Port port;
    QuayOccupancyEvaluator e;

    @Before
    public void setUp() throws Exception {
        Ship.resetShipRegistry();
        Ship ship1 = new BulkCarrier(3456789, "Glorious", "Switzerland",
                NauticalFlag.HOTEL, 90);
        Ship ship2 = new BulkCarrier(4567890, "Panda", "Japan",
                NauticalFlag.NOVEMBER, 10);
        Quay quay1 = new BulkQuay(1,100);
        Quay quay2 = new BulkQuay(2,100);
        quay1.shipArrives(ship1);
        port = new Port("Bob");
        port.addQuay(quay1);
        port.addQuay(quay2);
        port.addMovement(new ShipMovement(1, MovementDirection.INBOUND,ship2));

        e = new QuayOccupancyEvaluator(port);
    }

    @After
    public void tearDown() throws Exception {
        Cargo.resetCargoRegistry();
        Ship.resetShipRegistry();
    }
    @Rule
    public Timeout timeout = Timeout.seconds(1);

    @Deprecated
    @Test
    public void getQuaysOccupied() {
        assertEquals(1,e.getQuaysOccupied());
        for(int i = 0; i < 10;i++){
            port.elapseOneMinute();
        }
        assertEquals(2,e.getQuaysOccupied());
    }
}