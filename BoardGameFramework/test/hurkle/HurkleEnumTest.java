
package hurkle;

import junit.framework.TestCase;

/**
 *
 * @author BrianGouldsberry
 */
public class HurkleEnumTest extends TestCase {
    
    public HurkleEnumTest(String testName) {
        super(testName);
    }

    /**
     * Test of toAscii method, of class HurkleEnum.
     */
    public void testToAscii() {
        System.out.println("toAscii");
        assertEquals(" ", HurkleEnum.CLICKED.toAscii());
        assertEquals("X", HurkleEnum.UNCLICKED.toAscii());
        assertEquals("H", HurkleEnum.HURKLE.toAscii());
    }

}
