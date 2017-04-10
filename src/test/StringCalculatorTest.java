import main.java.StringCalculator;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Hugh on 3/26/2017.
 */
public class StringCalculatorTest {

    public StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void addEmptyString() {
        int expected = 0;
        int actual = stringCalculator.add("");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addOneNumber() {
        int expected = 1;
        int actual = stringCalculator.add("1");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addTwoNumbers() {
        int expected = 3;
        int actual = stringCalculator.add("1,2");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addUnknownAmountOfNumbers() {
        int expected = 20;
        int actual = stringCalculator.add("1,3,5,4,7");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addWithNewlineSplit() {
        int expected = 5;
        int actual = stringCalculator.add("2\n3");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addWithOtherDelimiters() {
        int expected = 3;
        int actual = stringCalculator.add("//;\n1;2");

        Assert.assertEquals(expected, actual);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void addWithOneNegativeNumberThrowsException() throws Exception {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Negatives not allowed: -1");

        stringCalculator.add("-1,2");
    }

    @Test
    public void addWithMultipleNegativeNumberThrowsException() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Negatives not allowed: -1 -2");

        stringCalculator.add("-1,-2");
    }

    @Test
    public void addWithNumberGreaterThan1001() {
        int expected = 1;
        int actual = stringCalculator.add("1,1001");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addWithAnyLengthDelimeter() {
        int expected = 5;
        int actual = stringCalculator.add("//[***]\n1***2***2");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addWithMultipleDelimeters() {
        int expected = 6;
        int actual = stringCalculator.add("//[***][%%%]\n1***2%%%3");

        Assert.assertEquals(expected, actual);
    }
}
