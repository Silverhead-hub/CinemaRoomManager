package cinema;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Cinema {
    private char[][] cinema;
    private final int rows;
    private final int seats;

    public int countTickets = 0;
    public double percent =  0.00;
    public int allBuyTicketPrice = 0;
    public int totalIncomes = 0;
    public int countSeats = 0;
    public int price = 0;
    public int row = 0;
    public int seat = 0;

    public Cinema(int rows, int seats) {
        this.rows = rows;
        this.seats = seats;
        cinema = new char[rows][seats];
        createCinemaHall(cinema);
    }

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        int rows = getIndex();
        System.out.println("Enter the number of seats in each row:");
        int seats = getIndex();

        Cinema cinema = new Cinema(rows, seats);
        cinema.menu();
    }

    public void createCinemaHall(char[][] cinema)
    {
        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
               cinema[i][j] = 'S';
            }
        }

        countSeats = rows*seats;
        if(countSeats <= 60){
            totalIncomes = countSeats * 10;
        }
        else {
            if(rows%2 == 0)
                totalIncomes = ((rows/2)*seats*10) + ((rows/2)*seats*8);
            else
                totalIncomes = ((rows/2)*seats*10) + ((rows/2+1)*seats*8);

        }
    }

    public void buyTicket(char[][] cinema, int rows, int seats){
        System.out.println("Enter a row number:");
        row = getIndex();
        System.out.println("Enter a seat number in that row:");
        seat = getIndex();



        if(countSeats <= 60){
            price = 10;
        }
        else {
            if (row > rows/2){
                price = 8;
            }
            else
                price = 10;
        }
        allBuyTicketPrice += price;
        System.out.println("Ticket price: $" + price);
        System.out.println();

       if(row > rows || seat > seats){
            System.out.println("Wrong input!");
            buyTicket(cinema, rows, seats);
        }
        else if(cinema[row-1][seat-1] != 'B') {
            cinema[row - 1][seat - 1] = 'B';
            countTickets++;
            percent = (double) countTickets/countSeats * 100;
        }
        else{
            System.out.println("That ticket has already been purchased!");
            buyTicket(cinema, rows, seats);
        }

    }


    public void menu(){

        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");

        int variant = getIndex();


        switch (variant){
            case 1 :
                printCinemaHall(cinema);
                menu();
                break;
            case 2 :
                buyTicket(cinema,rows,seats);
                menu();
                break;
            case 3:
                statistics();
                menu();
                break;
            case 0 :
                break;
        }
    }

    public void printCinemaHall(char[][]cinema){
        System.out.println("Cinema:");
        for(int k = 0; k <= cinema[0].length; k++){
            if(k == 0){
                System.out.print("  ");
            }
            else
                System.out.print(k + " ");

        }
        System.out.println();
        for (int i = 0; i < cinema.length; i++) {
            // Цикл по второй размерности выводит колонки - вывод одной строки
            System.out.print(i+1 + " ");
            for (int j = 0; j < cinema[i].length; j++) {
                // Используем оператор print - без перехода на следующую строку
                System.out.print(cinema[i][j]);
                if(j != cinema.length)
                    System.out.print(" ");
            }
            // Переход на следующую строку
            System.out.println();
        }
    }

    public void statistics(){
        System.out.println("Number of purchased tickets: " + countTickets);
        String formatDouble = new DecimalFormat("#0.00").format(percent);
        System.out.println("Percentage: " + formatDouble + "%");
        System.out.println("Current income: $" + allBuyTicketPrice );
        System.out.println("Total income: $" + totalIncomes);


    }

    public static int getIndex(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        return n;
    }
}