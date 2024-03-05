import java.util.Objects;
import java.util.Scanner;

public class Cinema {

    public static String[][] theaterSeats;
    public static int rows;
    public static int priceCutOff = 60;
    public static int seatsTotal;
    public static int rowCutOff;
    public static int seatsPerRow;

    public static int rowNum;

    public static int seatNum;

    public static int seatsPurchased;

    public static double seatPercentage;

    public static int cheapSeat = 0;

    public static int regSeat = 0;

    public static Scanner input = new Scanner(System.in);

    public static boolean contin = true;

    public static int currentIncome;

    public static int maxIncome;

    public static void calculateTotalTicketPrice() {
        if (seatsTotal > 60) {
            int rowHalf = rows - rowCutOff;
            int rowHalfPrice = rowHalf * seatsPerRow * 8;
            int rowFullPrice = rowCutOff * seatsPerRow * 10;
            maxIncome = rowHalfPrice + rowFullPrice;
        } else {
            maxIncome = seatsTotal * 10;
        }
    }

    public static void buildTheater() {
        System.out.println("Enter the number of rows:");
        rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = input.nextInt();
    }

    public static void assignSeat() {
        System.out.println("Enter a row number: ");
        rowNum = input.nextInt();
        System.out.println("Enter a seat number in that row: ");
        seatNum = input.nextInt();
        if (rowNum < 0 || seatsPerRow < 0) {
            System.out.println("Wrong input!");
            System.out.println();
            System.out.println("Enter a row number: ");
            rowNum = input.nextInt();
            System.out.println("Enter a seat number in that row: ");
            seatNum = input.nextInt();
        }
    }

    public static void menuOptions() {
        Scanner input2 = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int numChoice = input2.nextInt();
        System.out.println();
        switch (numChoice) {
            case 1:
                System.out.println("Cinema:");
                for (int i = 0; i < rows + 1; i++) {
                    for (int j = 0; j < seatsPerRow + 1; j++) {
                        System.out.print(theaterSeats[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println();

                break;
            case 2: assignSeat();
                try {
                    if (Objects.equals(theaterSeats[rowNum][seatNum], "B")) {
                        System.out.println("That ticket has already been purchased!");
                        System.out.println();
                        assignSeat();
                    } } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Wrong input!");
                    assignSeat();
                }
                if (seatsTotal > priceCutOff && rowNum > rowCutOff) {
                    System.out.println("Ticket price: $8");
                    cheapSeat += 8;
                } else if (seatsTotal < priceCutOff) {
                    System.out.println("Ticket price: $10");
                    regSeat += 10;
                } else {
                    System.out.println("Ticket price: $10");
                    regSeat += 10;
                }
                System.out.println();
                try {
                    theaterSeats[rowNum][seatNum] = "B";
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Wrong input!");
                    System.out.println();
                    assignSeat();
                    theaterSeats[rowNum][seatNum] = "B";
                    System.out.println();
                }
                seatsPurchased++;
                currentIncome = regSeat + cheapSeat;
                break;

            case 3:
                calculateTotalTicketPrice();
                seatPercentage = (double) seatsPurchased / (double) seatsTotal * 100;
                System.out.println("Number of purchased tickets: " + seatsPurchased);
                System.out.printf("Percentage: %.2f%% %n" , seatPercentage);
                System.out.println("Current income: $" + currentIncome);
                System.out.println("Total income: $" + maxIncome);
                System.out.println();
                break;
            case 0:
                contin = false;
                break;
        }
    }

    public void getMenu() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        rows = input.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seatsPerRow = input.nextInt();
        theaterSeats = new String[rows + 1][seatsPerRow + 1];
        rowCutOff = rows / 2;
        seatsTotal = rows * seatsPerRow;
        System.out.println();
        for (int i = 0; i < rows + 1; i++)
            for (int j = 0; j < seatsPerRow + 1; j++) {
                theaterSeats[0][0] = " ";
                theaterSeats[0][j] = String.valueOf(j);
                theaterSeats[i][0] = String.valueOf(i);
                theaterSeats[i][j] = "S";
            }
        while(contin) {
            menuOptions();
        }
    }
}

