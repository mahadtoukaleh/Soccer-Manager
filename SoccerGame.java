import java.util.Random;
import java.util.Scanner;

public class SoccerGame {
    private Team userTeam;
    private Scanner scanner;

    public SoccerGame() {
        scanner = new Scanner(System.in);

        // Prompt the user to enter their team's name
        System.out.println("Enter your team's name:");
        String teamName = scanner.nextLine();

        // Initialize the user's team with the chosen name and a default formation
        userTeam = new Team(teamName, "4-4-2");

        createInitialTeam();
    }

    public static void main(String[] args) {
        SoccerGame game = new SoccerGame();
        game.start();

    }

    private void createInitialTeam() {
        // Create some initial players for the user's team
        Player goalkeeper = new Player("John Smith", 50, "GK", 3);
        Player defender1 = new Player("David Johnson", 75, "DF", 4);
        Player defender2 = new Player("Michael Brown", 75, "DF", 3);
        Player defender3 = new Player("Marquinhos", 75, "DF", 4);
        Player defender4 = new Player("Ramos", 75, "DF", 3);
        Player midfielder1 = new Player("Daniel Davis", 100, "MF", 5);
        Player midfielder2 = new Player("Christopher Wilson", 50, "MF", 2);
        Player midfielder3 = new Player("Modric", 75, "MF", 4);
        Player midfielder4 = new Player("Bruno", 75, "MF", 3);
        Player forward1 = new Player("James Taylor", 75, "FW", 4);
        Player forward2 = new Player("Matthew Thompson", 100, "FW", 5);

        // Add the initial players to the user's team
        userTeam.addPlayer(goalkeeper);
        userTeam.addPlayer(defender1);
        userTeam.addPlayer(defender2);
        userTeam.addPlayer(defender3);
        userTeam.addPlayer(defender4);
        userTeam.addPlayer(midfielder1);
        userTeam.addPlayer(midfielder2);
        userTeam.addPlayer(midfielder3);
        userTeam.addPlayer(midfielder4);
        userTeam.addPlayer(forward1);
        userTeam.addPlayer(forward2);
    }



    public void start() {
        boolean quit = false;
        while (!quit) {
            System.out.println("Enter a number to choose an option:");
            System.out.println("1. View Team");
            System.out.println("2. Play Game");
            System.out.println("3. Buy Player");
            System.out.println("4. Sell Player");
            System.out.println("5. Check Coins");
            System.out.println("6. Train Player");
            System.out.println("7. Quit");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewTeam();
                    break;
                case 2:
                    if (userTeam.getPlayers().size() >= 11) {
                        playGame();
                    } else {
                        System.out.println("Your team does not have enough players to play a game. You need at least 11 players.");
                    }
                    break;
                case 3:
                    buyPlayer();
                    break;
                case 4:
                    sellPlayer();
                    break;
                case 5:
                    checkCoins();
                    break;
                case 6:
                    developPlayer();
                    break;
                case 7:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void viewTeam() {
        // Display the user's team's name, formation, and players
        System.out.println("Team: " + userTeam.getName());
        System.out.println("Formation: " + userTeam.getFormation().toString());
        System.out.println("Players:");
        for (Player player : userTeam.getPlayers()) {
            System.out.println(player.getName() + " (" + player.getPosition() + ")");
        }
    }

    private void playGame() {

        Random random = new Random();
        int userScore = random.nextInt(6); // Generate a random score for the user
        int opponentScore = random.nextInt(6); // Generate a random score for the opponent

        System.out.println(userTeam.getName() + " scored " + userScore + " goals.");
        System.out.println("Opponent team scored " + opponentScore + " goals.");

        if (userScore > opponentScore) {
            System.out.println("Congratulations! You won the game! \nYou win 100 Coins");
            userTeam.setCoins(userTeam.getCoins() + 100); // Award the user 100 coins for winning
            System.out.println("Total Balance: " + userTeam.getCoins());
        } else if (userScore < opponentScore) {
            System.out.println("Sorry, you lost the game. \nYou have " + userTeam.getCoins());
        } else {
            System.out.println("The game ended in a tie. You get 50 Coins!");
            userTeam.setCoins(userTeam.getCoins() + 50); // Award the user 50 coins for a tie
            System.out.println("Total Balance: " + userTeam.getCoins());
        }
    }


    private void buyPlayer() {
        if (userTeam.getPlayers().size() >= 11) {
            System.out.println("Your team is full. You cannot buy any more players.");
            return;
        }

        System.out.println("You have: " + userTeam.getCoins());

        System.out.println("Enter the name of the player you want to buy:");
        String name = scanner.nextLine();

        System.out.println("Enter the rating of the player you want to buy (1-5):");
        int rating = scanner.nextInt();
        scanner.nextLine();

        int price;
        switch (rating) {
            case 1:
            case 2:
                price = 25;
                break;
            case 3:
                price = 50;
                break;
            case 4:
                price = 75;
                break;
            case 5:
                price = 100;
                break;
            default:
                System.out.println("Invalid rating.");
                return;
        }

        if (userTeam.getCoins() < price) {
            System.out.println("You don't have enough coins to buy this player.");
            return;
        }

        System.out.println("Enter the position of the player you want to buy (GK, DF, MF, FW):");
        String position = scanner.nextLine();

        Player newPlayer = new Player(name, price, position, rating);

        userTeam.addPlayer(newPlayer);
        userTeam.setCoins(userTeam.getCoins() - price);

        System.out.println("Successfully bought " + name + " for " + price + " coins.");
    }


    private void sellPlayer() {
        System.out.println("Enter the name of the player you want to sell:");
        String playerName = scanner.nextLine();

        Player playerToRemove = null;
        for (Player player : userTeam.getPlayers()) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                playerToRemove = player;
                break;
            }
        }

        if (playerToRemove == null) {
            System.out.println("Could not find player with name " + playerName);
            return;
        }

        int sellPrice = playerToRemove.getPrice() / 1;
        userTeam.removePlayer(playerToRemove);
        userTeam.setCoins(userTeam.getCoins() + sellPrice);

        System.out.println("Successfully sold " + playerName + " for " + sellPrice + " coins.");
        System.out.println("You have: " + userTeam.getCoins());
    }

    private void checkCoins() {
        System.out.println("You currently have " + userTeam.getCoins() + " coins.");
    }

    private void developPlayer() {
        if (userTeam.getPlayers().size() == 0) {
            System.out.println("You don't have any players to develop.");
            return;
        }

        System.out.println("Enter the name of the player you want to develop:");
        String playerName = scanner.nextLine();

        Player playerToDevelop = null;
        for (Player player : userTeam.getPlayers()) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                playerToDevelop = player;
                break;
            }
        }

        if (playerToDevelop == null) {
            System.out.println("Could not find player with name " + playerName);
            return;
        }

        int cost = playerToDevelop.getRating() * 10;
        if (userTeam.getCoins() < cost) {
            System.out.println("You don't have enough coins to develop this player.");
            return;
        }

        System.out.println("Developing " + playerToDevelop.getName() + " will cost " + cost + " coins. Do you want to proceed? (Y/N)");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            userTeam.setCoins(userTeam.getCoins() - cost);
            playerToDevelop.setRating(playerToDevelop.getRating() + 1);
            System.out.println(playerToDevelop.getName() + "'s rating increased to " + playerToDevelop.getRating());
        } else {
            System.out.println("Player development canceled.");
        }
    }



}
