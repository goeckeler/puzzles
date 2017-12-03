package euler.p016;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * 2^(15) = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 *
 * What is the sum of the digits of the number 2^(1000)?
 */
public class Euler16
{
  public static void main(final String[] args) {
    System.out.println("Checksum of 11 is " + checksum("11"));

    BigDecimal decimal = new BigDecimal(Math.pow(2,1000));
    String number = decimal.toPlainString();
    System.out.println(number);
//      "10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376";

    System.out.println("Checksum of 2^1000 is " + NumberFormat.getIntegerInstance().format(checksum(number)));
  }

  public static int checksum(final String number) {
    int checksum = 0;
    for (int digit = 0; digit < number.length(); ++digit) {
      checksum += Integer.valueOf(number.substring(digit, digit + 1));
    }
    return checksum;
  }
}
