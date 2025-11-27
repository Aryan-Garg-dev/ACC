package leetcode;

public class Q2043 {
  static class Bank {
    private final long[] balance;

    public Bank(long[] balance) {
      this.balance = balance;
    }

    protected boolean _isAccountNumValid(int account){
      return (account >= 1 && account <= this.balance.length);
    }

    public boolean transfer(int account1, int account2, long money) {
      if (
        _isAccountNumValid(account1) &&
        _isAccountNumValid(account2) &&
        this.balance[account1 - 1] >= money
      ) {
        this.balance[account1 - 1] -= money;
        this.balance[account2 - 1] += money;
        return true;
      }
      return false;
    }

    public boolean deposit(int account, long money) {
      if (_isAccountNumValid(account)){
        this.balance[account - 1] += money;
        return true;
      }
      return false;
    }

    public boolean withdraw(int account, long money) {
      if (_isAccountNumValid(account) && this.balance[account - 1] >= money){
        this.balance[account - 1] -= money;
        return true;
      }
      return false;
    }
  }

  public static void main(String[] args) {
    Bank bank = new Bank(new long[] {1000L});
  }
}
