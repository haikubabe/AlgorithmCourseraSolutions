package graph_search.week4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class TwoSum
{

    private static int NUM_ELEMENTS = 1000000;
    private static long[] arr = new long[NUM_ELEMENTS];
    private static int MIN = -10000;
    private static int MAX = 10000;

    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("graph_search/week4/input.txt"));
        String line;
        int i=0;
        while ((line = br.readLine()) != null) {
            arr[i++] = Long.parseLong(line.split("\\s+")[0]);
        }
        Arrays.sort(arr);
        boolean[] found = new boolean[MAX-MIN+1];
        for (int j=MIN;j<=MAX;j++) {
            found[j-MIN] = false;
        }
        int left = 0, right = NUM_ELEMENTS-1;
        while (left < right) {
            long sum = arr[left] + arr[right];
            if (sum < MIN) {
                left++;
            } else if (sum > MAX) {
                right--;
            } else {
                if (arr[left] != arr[right]) {
                    found[(int)sum-MIN] = true;
                }
                int current_left = left;
                int current_right = right;
                left++;
                sum = arr[left] + arr[right];
                while (sum >= MIN && sum <= MAX) {
                    if (arr[left] != arr[right]) {
                        found[(int)sum-MIN] = true;
                    }
                    left++;
                    if (left > NUM_ELEMENTS-1) {
                        break;
                    }
                    sum = arr[left] + arr[right];
                }
                left = current_left;
                right--;
                sum = arr[left] + arr[right];
                while (sum >= MIN && sum <= MAX) {
                    if (arr[left] != arr[right]) {
                        found[(int)sum-MIN] = true;
                    }
                    right--;
                    if (right < 0) {
                        break;
                    }
                    sum = arr[left] + arr[right];
                }
                right = current_right;
                left++;
                right--;
            }
        }
        int count=0;
        for (int j=MIN;j<=MAX;j++) {
            if (found[j-MIN]) {
                count++;
            }
        }
        System.out.println(count);
    }

}
