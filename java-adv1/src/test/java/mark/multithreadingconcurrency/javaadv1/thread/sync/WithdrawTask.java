package mark.multithreadingconcurrency.javaadv1.thread.sync;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WithdrawTask implements Runnable {

    private BankAccount account;
    private int amount;

    @Override
    public void run() {
        account.withdraw(amount);
    }
}
