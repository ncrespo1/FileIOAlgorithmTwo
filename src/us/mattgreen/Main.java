package us.mattgreen;

import java.util.Scanner;

/**
 * @author Nic Crespo
 * @version 2017.3.3
 */
public class Main {
    private static String line = "";
    private static String line2 = "";
    private final static FileInput cardAccts = new FileInput("movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("movie_purchases.csv");
    private final static FileInput cardRatings = new FileInput("movie_rating.csv");
    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        String line;

        String[] fields;
        boolean first = true;
        int[] nums = new int[2];
        int[] nums2 = new int[2];
        System.out.format("%8s  %-18s %6s %6s %4s\n","Account","Name", "Movies", "Points", "Ratings");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            findPurchases(first,fields[0], nums);
            findRatings(first,fields[0], nums2);
            first = false;

            if(nums2[0] == 0) {
                System.out.format("00%6s  %-18s  %2d   %4d     %4d\n", fields[0], fields[1], nums[0], nums[1], nums2[1]);
            } else if (nums2[0] > 0) {
                System.out.format("00%6s  %-18s  %2d   %4d     %.2f\n", fields[0], fields[1], nums[0], nums[1], ((double) nums2[1] / (double) nums2[0]));
            }
        }
    }

    /**
     *
     * @param first
     * @param acct  account number
     * @param nums  movies purchased
     */
    public static void findPurchases(boolean first, String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
       //String line = "";
        String[] fields;
        boolean done = false;
        if (first) {
            line = cardPurchases.fileReadLine();
        }
        while ((line != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[2]);
                line = cardPurchases.fileReadLine();
            }

        }
    }

    /**
     *
     * @param first
     * @param acct  account number
     * @param nums  movie ratings
     */
    public static void findRatings(boolean first, String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;

        String[] fields;
        boolean done = false;
        if (first) {
            line2 = cardRatings.fileReadLine();
        }
        while ((line2 != null) && !(done)) {
            fields = line2.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[1]);
                line2 = cardRatings.fileReadLine();
            }

        }
    }
}