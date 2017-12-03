package euler.p019;

/*
 * You are given the following information, but you may prefer to do some research for yourself. 1 Jan 1900 was a
 * Monday. Thirty days has September, April, June and November. All the rest have thirty-one, Saving February alone,
 * Which has twenty-eight, rain or shine. And on leap years, twenty-nine. A leap year occurs on any year evenly
 * divisible by 4, but not on a century unless it is divisible by 400. How many Sundays fell on the first of the month
 * during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
public class Euler19
{
  private static final int[] days = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  public static void main(final String[] args) {
    // January 1st 1901 was a tuesday, sunday is defined as 0
    int dayOfWeek = 2;

    int sundays = 0;
    for (int year = 1901; year < 2001; ++year) {
      for (int month = 0; month < 12; ++month) {
        dayOfWeek += days[month];
        if (month == 1 && year % 4 == 0) {
          ++dayOfWeek;
        }

        dayOfWeek %= 7; // seven days a week

        if (dayOfWeek == 0) ++sundays;
      }
    }

    System.out.println("There are " + sundays + " sundays on the first of the month in the 20th century");
  }
}
