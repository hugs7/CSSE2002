package portsim.ship;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import portsim.cargo.*;
import portsim.port.BulkQuay;
import portsim.port.ContainerQuay;
import portsim.util.NoSuchCargoException;

import static org.junit.Assert.*;

public class BulkCarrierTest {
    private BulkCarrier bulkCarrier;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        bulkCarrier = null;
    }

    @Test
    public void testGetName() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertEquals("CSSE2002", bulkCarrier.getName());
    }

    @Test
    public void testGetImoNumber() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertEquals(1234567, bulkCarrier.getImoNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidImoNumber1() throws IllegalArgumentException {
        bulkCarrier = new BulkCarrier(2, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidImoNumber2() throws IllegalArgumentException{
        bulkCarrier = new BulkCarrier(-1, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
    }

    @Test
    public void testGetOriginFlag() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertEquals("Australia", bulkCarrier.getOriginFlag());
    }

    @Test
    public void testGetFlag() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertEquals(NauticalFlag.BRAVO, bulkCarrier.getFlag());
    }

    @Test
    public void testCanDock() {
        BulkQuay bulkQuay = new BulkQuay(123, 2000);
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertTrue(bulkCarrier.canDock(bulkQuay));
    }

    @Test
    public void testErrorCanDock1() {
        ContainerQuay containerQuay = new ContainerQuay(123, 100);
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertFalse(bulkCarrier.canDock(containerQuay));
    }

    @Test
    public void testErrorCanDock2() {
        BulkQuay bulkQuay = new BulkQuay(123, 2000);
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 3000);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 2500, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertFalse(bulkCarrier.canDock(bulkQuay));
    }

    @Test
    public void testCanLoad() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        assertTrue(bulkCarrier.canLoad(bulkCargo));
    }

    @Test
    public void testErrorCanLoad1() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertFalse(bulkCarrier.canLoad(bulkCargo));
    }

    @Test
    public void testErrorCanLoad2() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        Container container = new Container(1, "Australia", ContainerType.TANKER);
        assertFalse(bulkCarrier.canLoad(container));
    }

    @Test
    public void testErrorCanLoad3() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 2000, BulkCargoType.COAL);
        assertFalse(bulkCarrier.canLoad(bulkCargo));
    }

    @Test
    public void testErrorCanLoad4() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "UQ", 123, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertFalse(bulkCarrier.canLoad(bulkCargo));
    }

    @Test
    public void testLoadCargo() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertFalse(bulkCarrier.canLoad(bulkCargo));
    }

    @Test
    public void testUnloadCargo() throws NoSuchCargoException {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertEquals(bulkCargo, bulkCarrier.unloadCargo());
        assertNull(bulkCarrier.getCargo());
    }

    @Test(expected = NoSuchCargoException.class)
    public void testExceptionUnloadCargo() throws NoSuchCargoException {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        bulkCarrier.unloadCargo();
    }

    @Test
    public void testGetCargo() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertEquals(bulkCargo, bulkCarrier.getCargo());
    }

    @Test
    public void testGetNullCargo() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        assertNull(bulkCarrier.getCargo());
    }

    @Test
    public void testToString() {
        bulkCarrier = new BulkCarrier(1234567, "CSSE2002", "Australia", NauticalFlag.BRAVO, 1234);
        BulkCargo bulkCargo = new BulkCargo(1, "Australia", 123, BulkCargoType.COAL);
        bulkCarrier.loadCargo(bulkCargo);
        assertEquals("BulkCarrier CSSE2002 from Australia [BRAVO] carrying COAL", bulkCarrier.toString());
    }
}