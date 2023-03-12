import java.util.ArrayList;

public class Team {
    private String name;
    private ArrayList<Player> players;
    private int coins;
    private String formation;

    public Team(String name, String formation) {
        this.name = name;
        this.players = new ArrayList<>();
        this.coins = 100;
        this.formation = formation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public int calculateTeamRating() {
        int ratingSum = 0;
        for (Player player : players) {
            ratingSum += player.getRating();
        }
        return ratingSum / players.size();
    }
}
