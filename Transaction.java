package SimpleBankingApplicationBackend;
import java.util.Date;


class Transaction {
    private String type;
    private double amount;
    private Date timestamp;
    private String description;

    public Transaction(String type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = new Date();
    }

    // Getters
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public Date getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("[%s] %s: $%.2f - %s", timestamp.toString(), type, amount, description);
    }
}