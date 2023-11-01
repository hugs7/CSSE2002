package portsim.port;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.cargo.BulkCargo;
import portsim.cargo.BulkCargoType;
import portsim.ship.BulkCarrier;
import portsim.ship.NauticalFlag;

import static org.junit.Assert.*;

public class BulkQuayTest {
    private BulkQuay bulkQuay;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        bulkQuay = null;
    }

    @Test
    public void testGetId() {
        bulkQuay = new BulkQuay(1, 2000);
        assertEquals(1, bulkQuay.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidId() throws IllegalArgumentException {
        bulkQuay = new BulkQuay(-1, 2000);
    }

    @Test
    public void testShipArrives() {
        bulkQuay = new BulkQuay(1, 2000);
        BulkCarrier bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 2000);
        bulkQuay.shipArrives(bulkCarrier);
        assertFalse(bulkQuay.isEmpty());
    }

    @Test
    public void testShipDeparts() {
        bulkQuay = new BulkQuay(1, 2000);
        BulkCarrier bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 2000);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 1500, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        bulkQuay.shipArrives(bulkCarrier);
        assertEquals(bulkCarrier, bulkQuay.shipDeparts());
    }

    @Test
    public void testShipDepartsNull() {
        bulkQuay = new BulkQuay(1, 2000);
        assertNull(bulkQuay.shipDeparts());
    }

    @Test
    public void testIsEmptyTrue() {
        bulkQuay = new BulkQuay(1, 2000);
        assertTrue(bulkQuay.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        bulkQuay = new BulkQuay(1, 2000);
        BulkCarrier bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 2000);
        bulkQuay.shipArrives(bulkCarrier);
        assertFalse(bulkQuay.isEmpty());
    }

    @Test
    public void testGetShip() {
        bulkQuay = new BulkQuay(1, 2000);
        BulkCarrier bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 2000);
        bulkQuay.shipArrives(bulkCarrier);
        assertSame(bulkCarrier, bulkQuay.getShip());
    }

    @Test
    public void testToString() {
        bulkQuay = new BulkQuay(1, 2000);
        assertEquals("BulkQuay 1 [Ship: None] - 2000", bulkQuay.toString());
    }

    @Test
    public void testGetMaxTonnage() {
        bulkQuay = new BulkQuay(1, 2000);
        assertEquals(2000, bulkQuay.getMaxTonnage());
    }
}