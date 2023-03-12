public class Player {
    private String name;
    private int price;
    private String position;
    private int rating;
    private Stats stats;

    public Player(String name, int price, String position, int rating) {
        this.name = name;
        this.price = price;
        this.position = position;
        this.rating = rating;
        this.stats = generateStats(position, rating);
    }

    private Stats generateStats(String position, int rating) {
        int stat1 = 0;
        int stat2 = 0;
        int stat3 = 0;

        if (position.equals("GK")) {
            stat1 = rating * 3;
            stat2 = rating * 3;
            stat3 = rating * 4;
        } else if (position.equals("DF")) {
            stat1 = rating * 3;
            stat2 = rating * 4;
            stat3 = rating * 3;
        } else if (position.equals("MF")) {
            stat1 = rating * 4;
            stat2 = rating * 3;
            stat3 = rating * 3;
        } else if (position.equals("FW")) {
            stat1 = rating * 4;
            stat2 = rating * 4;
            stat3 = rating * 2;
        }

        return new Stats(stat1, stat2, stat3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice() {
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
                throw new IllegalArgumentException("Invalid rating: " + rating);
        }
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
        this.stats = generateStats(position, rating);
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }
}