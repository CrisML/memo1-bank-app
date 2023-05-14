package com.aninfo.repository;

import com.aninfo.model.Account;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import com.aninfo.model.Transaction;
import java.util.List;

@RepositoryRestResource
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Override
    List<Transaction> findAll();

    List<Transaction> findTransactionsByCbu(Long cbu);
}
