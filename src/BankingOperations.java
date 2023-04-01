public interface BankingOperations {
    void deposit(int amount, int currency);
    void withdraw(int amount, int currency);
    void currencyTransfer(int currencyCurrent, int amount, int currencyAnother);
}
