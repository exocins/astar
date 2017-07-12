package astar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapPointTest {
    MapPoint aMapPoint;

    @Before
    public void setUp() throws Exception {
        aMapPoint = new MapPoint(2,3); // create a known point
    }

    @After
    public void tearDown() throws Exception {
        aMapPoint = null;
    }

    @Test
    public void test() {
        assertEquals(2, aMapPoint.x, 0);
        assertEquals(3, aMapPoint.y, 0);
        // test a known point to String
        assertEquals("MapPoint.toString","(2,3)", aMapPoint.toString());
    }

}
