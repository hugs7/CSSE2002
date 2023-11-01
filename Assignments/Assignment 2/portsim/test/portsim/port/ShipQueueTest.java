package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.ship.BulkCarrier;
import portsim.ship.ContainerShip;
import portsim.ship.NauticalFlag;
import portsim.ship.Ship;
import portsim.util.BadEncodingException;

import static org.junit.Assert.*;

public class ShipQueueTest {
    ShipQueue testShipQueue;
    ShipQueue testShipQueue2;

    Ship testShip0;
    Ship testShip1;
    Ship testShip2;
    Ship testShip3;
    Ship testShip4;
    Ship testShip5;
    Ship testShip6;
    Ship testShip7;
    Ship testShip8;
    Ship testShip9;

    @Before
    public void setUp() throws Exception {
        testShipQueue = new ShipQueue();

        testShip0 = new ContainerShip(1234567, "Dangerous Cargo Ship",
                "UQ", NauticalFlag.BRAVO, 2000);
        testShip1 = new ContainerShip(4698512, "Medical Assistance Ship",
                "UQ", NauticalFlag.BRAVO, 2000);
        testShip2 = new ContainerShip(4698513, "Ready Docked Ship",
                "UQ", NauticalFlag.BRAVO, 2000);
        testShip3 = new ContainerShip(4685433, "Ship",
                "UQ", NauticalFlag.NOVEMBER, 2000);
        testShip4 = new BulkCarrier(3243244, "BulkCarrier",
                "UQ", NauticalFlag.NOVEMBER, 2000);
        testShip5 = new ContainerShip(4698312, "Container Ship 1",
                "UQ", NauticalFlag.NOVEMBER, 2000);
        testShip6 = new ContainerShip(5697035, "Container Ship 2",
                "UQ", NauticalFlag.NOVEMBER, 2500);
        testShip7 = new BulkCarrier(1235567, "BulkCarrier 0",
                "UQ", NauticalFlag.NOVEMBER, 2800);
        testShip8 = new BulkCarrier(6698512, "BulkCarrier 1",
                "UQ", NauticalFlag.NOVEMBER, 2800);
        testShip9 = new BulkCarrier(9697035, "BulkCarrier 2",
                "UQ", NauticalFlag.NOVEMBER, 2800);

    }

    // Test peek()
    @Test
    public void testReturnDangerousCargoShip() {
        testShipQueue.add(testShip0);
        testShipQueue.add(testShip1);

        assertEquals(testShip0, testShipQueue.peek());
    }

    @Test
    public void testReturnMedicalShip() {
        testShipQueue.add(testShip1);
        testShipQueue.add(testShip2);

        assertEquals(testShip1, testShipQueue.peek());
    }

    @Test
    public void testReturnReadyDockedShip() {
        testShipQueue.add(testShip2);
        testShipQueue.add(testShip3);

        assertEquals(testShip2, testShipQueue.peek());
    }

    @Test
    public void testReturnContainerShip() {
        testShipQueue.add(testShip4);
        testShipQueue.add(testShip5);
        testShipQueue.add(testShip6);

        assertEquals(testShip5, testShipQueue.peek());
    }

    @Test
    public void testReturnFirstShip() {
        testShipQueue.add(testShip7);
        testShipQueue.add(testShip8);
        testShipQueue.add(testShip9);

        assertEquals(testShip7, testShipQueue.peek());
    }

    @Test
    public void testNoShipsInQueue() {
        assertNull(testShipQueue.peek());
    }

    // Test add and get
    @Test
    public void testAddShip() {
        testShipQueue.add(testShip7);

        assertEquals(testShipQueue.getShipQueue(), testShipQueue.getShipQueue());
    }

    // Equals
    @Test
    public void testEquals() {
        testShipQueue.add(testShip7);
        testShipQueue.add(testShip8);

        ShipQueue testShipQueueTwo = new ShipQueue();
        testShipQueueTwo.add(testShip7);
        testShipQueueTwo.add(testShip8);

        assertEquals(testShipQueue, testShipQueueTwo);
    }

    @Test
    public void testEqualsNull() {
        ShipQueue testShipQueue2 = new ShipQueue();
        ShipQueue testShipQueue3 = null;

        assertNotEquals(testShipQueue2, testShipQueue3);
    }

    // Test Hash Code
    @Test
    public void testHashCodeEquals() {
        testShipQueue.add(testShip7);
        testShipQueue.add(testShip8);

        ShipQueue testShipQueueTwo = new ShipQueue();
        testShipQueueTwo.add(testShip7);
        testShipQueueTwo.add(testShip8);

        assertEquals(testShipQueue.hashCode(), testShipQueueTwo.hashCode());
    }

    @Test
    public void testHashCodeNotEquals() {
        testShipQueue.add(testShip7);
        testShipQueue.add(testShip8);

        ShipQueue testShipQueueTwo = new ShipQueue();
        testShipQueueTwo.add(testShip8);
        testShipQueueTwo.add(testShip6);

        assertNotEquals(testShipQueue.hashCode(), testShipQueueTwo.hashCode());
    }

    // Encode
    @Test
    public void testEncode() {
        testShipQueue.add(testShip0);
        testShipQueue.add(testShip1);

        assertEquals("ShipQueue:2:1234567,4698512", testShipQueue.encode());
    }

    @Test
    public void testEncodeNoShips() {
        assertEquals("ShipQueue:0:", testShipQueue.encode());
    }

    // From String
    @Test
    public void testFromString() {
        ShipQueue testShipQueue;
        ShipQueue expectedShipQueue = new ShipQueue();
        expectedShipQueue.add(testShip0);
        expectedShipQueue.add(testShip1);
        expectedShipQueue.add(testShip2);

        try {
            testShipQueue = ShipQueue.fromString("ShipQueue:3:1234567,4698512,4698513");
            assertEquals(expectedShipQueue.toString(), testShipQueue.toString());
        } catch (BadEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test (expected = BadEncodingException.class)
    public void testNotShipQueue() throws BadEncodingException {
        ShipQueue testShipQueue;
        ShipQueue expectedShipQueue = new ShipQueue();
        expectedShipQueue.add(testShip0);
        expectedShipQueue.add(testShip1);
        expectedShipQueue.add(testShip2);

        testShipQueue = ShipQueue.fromString("notShipQueue:3:1234567,4698512,4698513");

    }

    @Test
    public void testFromStringNoShips() {
        ShipQueue testShipQueue;
        try {
            testShipQueue = ShipQueue.fromString("ShipQueue:0:");
            assertEquals(new ShipQueue(), testShipQueue);
        } catch (BadEncodingException e) {
            e.printStackTrace();
        }

    }

    @Test (expected = BadEncodingException.class)
    public void testFromStringThrowsException() throws BadEncodingException {
        ShipQueue.fromString("ShipQueue:4:1234567,4698512,7654321");
    }

    @Test (expected = BadEncodingException.class)
    public void testFromStringAdditional() throws BadEncodingException {
        ShipQueue.fromString("ShipQueue:3:1234567,4698512,7654321:extra");
    }

    @Test (expected = BadEncodingException.class)
    public void testFromStringAdditional2() throws BadEncodingException {
        ShipQueue.fromString("ShipQueue:0");
    }

    @After
    public void tearDown() throws Exception {
        testShipQueue = null;
        Ship.resetShipRegistry();
    }

}