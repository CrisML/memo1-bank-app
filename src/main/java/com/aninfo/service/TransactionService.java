package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.exceptions.InvalidTransactionTypeException;
import com.aninfo.model.Account;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.aninfo.model.Transaction;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    public Transaction createTransaction(Transaction transaction){
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
        return transactionRepository.findById(id);
    }
    public void save(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public void deleteById(Long id){
        transactionRepository.deleteById(id);
    }

}
