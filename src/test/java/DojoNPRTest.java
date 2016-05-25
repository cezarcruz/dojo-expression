import br.com.dojos.npr.DojoNPR;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by cezar on 18/05/16.
 */
public class DojoNPRTest {

    @Test
    public void testExpressionChange() {
        DojoNPR dojoNPR = new DojoNPR();
        assertEquals("ab-", dojoNPR.changeExpression("a-b"));
        assertEquals("abc--", dojoNPR.changeExpression("a-b-c"));
        assertEquals("abcd*--", dojoNPR.changeExpression("a-b-c*d"));
        assertEquals("ab+", dojoNPR.changeExpression("a+b"));
        assertEquals("ab*", dojoNPR.changeExpression("a*b"));
        assertEquals("ab/", dojoNPR.changeExpression("a/b"));
        assertEquals("abc*+", dojoNPR.changeExpression("a+b*c"));
        assertEquals("ab*c+", dojoNPR.changeExpression("a*b+c"));
        assertEquals("ab*cd/+", dojoNPR.changeExpression("a*b+c/d"));
        assertEquals("ab*cd/+ef*-", dojoNPR.changeExpression("a*b+c/d-e*f"));
        assertEquals("abc*-", dojoNPR.changeExpression("a-(b*c)"));
    }

    @Test
    public void solveExpression() {
        DojoNPR dojoNPR = new DojoNPR();
        assertEquals("1", dojoNPR.solveExpression("2 - 1"));
        assertEquals("3", dojoNPR.solveExpression("2 + 1"));
        assertEquals("2", dojoNPR.solveExpression("2 + 1 - 1"));
        assertEquals("4", dojoNPR.solveExpression("2 + 1 * 2"));
        assertEquals("4", dojoNPR.solveExpression("2 * 1 * 2"));
        assertEquals("4", dojoNPR.solveExpression("2 * 1 + 2"));
        assertEquals("-4", dojoNPR.solveExpression("2-(2*3)"));
        assertEquals("7", dojoNPR.solveExpression("(2 / 2)+(2*3)"));
    }

}
