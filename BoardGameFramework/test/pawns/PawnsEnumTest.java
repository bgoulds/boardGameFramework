
package pawns;

import junit.framework.TestCase;

/**
 *
 * @author BrianGouldsberry
 */
public class PawnsEnumTest extends TestCase {
    
    public PawnsEnumTest(String testName) {
        super(testName);
    }
    /**
     * Test of toAscii method, of class HurkleEnum.
     */
    public void testToAscii() {
        System.out.println("toAscii");
        assertEquals("B", PawnsEnum.BLACK.toAscii());
        assertEquals("W", PawnsEnum.WHITE.toAscii());
        assertEquals("-", PawnsEnum.NONE.toAscii());
    }

}
