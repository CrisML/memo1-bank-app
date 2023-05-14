package com.aninfo.service;

import com.aninfo.exceptions.*;
import com.aninfo.model.Account;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aninfo.model.Transaction;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction){

        Optional<Account> account = accountService.findById(transaction.getCbu());
        if (account.isEmpty()){
            throw new AccountNotValidException("Invalid Account CBU");
        }

        if(transaction.getCategory().equalsIgnoreCase("withdraw")){
            accountService.withdraw(transaction.getCbu(), transaction.getAmount());
        }else if(transaction.getCategory().equalsIgnoreCase("deposit")){
            accountService.deposit(transaction.getCbu(), transaction.getAmount());
        }else{
            throw new InvalidTransactionTypeException("Transaction type is invalid");
        }
        return transactionRepository.save(transaction);
    }

    public Collection<Transaction> getTransactions(){
        return this.transactionRepository.findAll();
    }

    public Collection<Transaction> getTransactionsByCbu(Long cbu){
        return this.transactionRepository.findTransactionsByCbu(cbu);
    }

    public Optional<Transaction>  getTransactionById(Long id) {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()){
            throw new TransactionNotFoundException("Transaction Not Found");
        }
        return transaction;
    }
    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id){
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if(transaction.isEmpty()){
            throw new TransactionNotFoundException("Transaction Not Found");
        }
        transactionRepository.deleteById(id);
    }

}
