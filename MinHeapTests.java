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
    public void testAdd2() {
        // Before : [null, 0, 7, 14, 21, 28, 35, 42, 49, 56, 63, 70, 77]
        // Expected : [null, 0, 7, 14, 21, 28, 35, 42, 49, 56, 63, 70, 77, 84, null, null, null, null, null, null, null, null, null, null, null, null]

        minHeap.add(0);
        minHeap.add(7);
        minHeap.add(14);
        minHeap.add(21);
        minHeap.add(28);
        minHeap.add(35);
        minHeap.add(42);
        minHeap.add(49);
        minHeap.add(56);
        minHeap.add(63);
        minHeap.add(70);
        minHeap.add(77);
        minHeap.add(84);

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY * 2];
        expected[1] = 0;
        expected[2] = 7;
        expected[3] = 14;
        expected[4] = 21;
        expected[5] = 28;
        expected[6] = 35;
        expected[7] = 42;
        expected[8] = 49;
        expected[9] = 56;
        expected[10] = 63;
        expected[11] = 70;
        expected[12] = 77;
        expected[13] = 84;

        System.out.println(Arrays.toString(minHeap.getBackingArray()));
        assertEquals(26, minHeap.getBackingArray().length);
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