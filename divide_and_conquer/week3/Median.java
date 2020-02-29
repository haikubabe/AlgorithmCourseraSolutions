package divide_and_conquer.week3;

/**
 * median of three elements i.e the one whose value is in between the others two
 */
public class Median
{

    private static int median(int a, int b, int c) {
        if (a>b) {
            if (b>c) {
                return b;
            } else {
                return Math.min(a, c);
            }
        } else {
            if (b<c) {
                return b;
            } else {
                return Math.max(a, c);
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println(median(1,2,3));
        System.out.println(median(3,2,1));
        System.out.println(median(2,3,1));
        System.out.println(median(-2,-8,20));
        System.out.println(median(15,20,10));
    }
}
