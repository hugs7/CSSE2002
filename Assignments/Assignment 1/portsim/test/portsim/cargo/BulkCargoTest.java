package portsim.cargo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BulkCargoTest {
    private BulkCargo bulkCargo;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {
        bulkCargo = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidID() {
        bulkCargo = new BulkCargo(-1, "UQ", 1234, BulkCargoType.COAL);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidTonnage() {
        bulkCargo = new BulkCargo(1, "UQ", -123, BulkCargoType.COAL);
    }

    @Test
    public void testGetId() {
        bulkCargo = new BulkCargo(1, "UQ", 1234, BulkCargoType.COAL);
        assertEquals(1, bulkCargo.getId());
    }

    @Test
    public void testGetDestination() {
        bulkCargo = new BulkCargo(1, "UQ", 1234, BulkCargoType.COAL);
        assertEquals("UQ", bulkCargo.getDestination());
    }

    @Test
    public void testToString() {
        bulkCargo = new BulkCargo(1, "UQ", 1234, BulkCargoType.COAL);
        assertEquals("BulkCargo 1 to UQ [COAL - 1234]", bulkCargo.toString());
    }

    @Test
    public void testGetTonnage() {
        bulkCargo = new BulkCargo(1, "UQ", 1234, BulkCargoType.COAL);
        assertEquals(1234, bulkCargo.getTonnage());
    }

    @Test
    public void testGetType() {
        bulkCargo = new BulkCargo(1, "UQ", 1234, BulkCargoType.COAL);
        assertEquals(BulkCargoType.COAL, bulkCargo.getType());
    }
}