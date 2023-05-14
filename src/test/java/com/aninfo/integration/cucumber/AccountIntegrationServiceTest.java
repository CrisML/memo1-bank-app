package com.aninfo.integration.cucumber;

import com.aninfo.Memo1BankApp;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.service.AccountService;
import com.aninfo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@ContextConfiguration(classes = Memo1BankApp.class)
@WebAppConfiguration
public class AccountIntegrationServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    Account createAccount(Double balance) {
        return accountService.createAccount(new Account(balance));
    }

    Account withdraw(Account account, Double sum) {
        Transaction transaction = new Transaction();
        transaction.setCbu(account.getCbu());
        transaction.setAmount(sum);
        transaction.setCategory("withdraw");
        transactionService.createTransaction(transaction);

        Optional<Account> newAccount = accountService.findById(account.getCbu());
        return newAccount.get();
    }

    Account deposit(Account account, Double sum) {
        Transaction transaction = new Transaction();
        transaction.setCbu(account.getCbu());
        transaction.setAmount(sum);
        transaction.setCategory("deposit");
        transactionService.createTransaction(transaction);

        Optional<Account> newAccount = accountService.findById(account.getCbu());
        return newAccount.get();
    }

}
