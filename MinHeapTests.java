import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MinHeapTests {

    private static final int TIMEOUT = 200;
    private MinHeap<Integer> minHeap;

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                10
               /  \
              25  35
             /  \
            40  30
        */
        minHeap.add(40);
        minHeap.add(35);
        minHeap.add(30);
        minHeap.add(10);
        minHeap.add(25);

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY];
        expected[1] = 10;
        expected[2] = 25;
        expected[3] = 35;
        expected[4] = 40;
        expected[5] = 30;
        System.out.println(Arrays.toString(minHeap.getBackingArray()));
        assertEquals(5, minHeap.size());
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                89
               /  \                 17
              64  43     --->      /
             /  \                 15
            15  17
        */
        minHeap.add(43);
        minHeap.add(15);
        minHeap.add(64);
        minHeap.add(17);
        minHeap.add(89);

        assertEquals((Integer) 89, minHeap.remove());
        assertEquals((Integer) 64, minHeap.remove());
        assertEquals((Integer) 43, minHeap.remove());
        assertEquals(2, minHeap.size());

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY];
        expected[1] = new Integer(17);
        expected[2] = new Integer(15);
        assertArrayEquals(expected, minHeap.getBackingArray());
    }
}