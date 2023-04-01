public class BankingApplication {
    public static void main(String[] args) {
        User user = new User(1, "Ivan", "9832");
        BankAccount bank = new BankAccount(user);
        bank.showMenu();
    }
}
