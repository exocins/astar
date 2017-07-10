package astar;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TileNodeTest {
    TileNode aTileNode;
    TileNode aParentNode;
    MapPoint aTilePoint;
    MapPoint aParentPoint;
    @Before
    public void setUp() throws Exception {
        aTilePoint = new MapPoint(2,3);
        aTileNode = new TileNode( aTilePoint );
        aTileNode.setHCost(14);
        aTileNode.setGCost(10);
        aParentPoint = new MapPoint(1,2);
        aParentNode = new TileNode( aParentPoint );
        aParentNode.setHCost(14);
        aParentNode.setGCost(0);
    }

    @After
    public void tearDown() throws Exception {
        aTilePoint = null;
        aTileNode = null;
        aParentPoint = null;
        aParentNode = null;
    }

    @Test
    public void test() {
        assertEquals(2, aTileNode.getMapPoint().x, 0);
        assertEquals(3, aTileNode.getMapPoint().y, 0);
        assertEquals(24, aTileNode.getFCost(), 0);

        aTileNode.setParent(aParentNode);
        assertEquals(1, aTileNode.getParentNode().getMapPoint().x, 0);
        assertEquals(2, aTileNode.getParentNode().getMapPoint().y, 0);
        assertEquals(10, aTileNode.calculateGMoveCost(aParentNode), 0);
        assertEquals(2, aTileNode.calculateHCost(aParentNode), 0);
    }

}
