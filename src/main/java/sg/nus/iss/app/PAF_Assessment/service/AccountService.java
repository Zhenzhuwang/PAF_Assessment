package sg.nus.iss.app.PAF_Assessment.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.app.PAF_Assessment.model.Account;
import sg.nus.iss.app.PAF_Assessment.repository.AccountRepository;

// @Service
// public class AccountService {
    
//     @Autowired
//     private AccountRepository accountRepository;

//     public Account createAccount (Account acc) throws Exception {
//         String accId= UUID.randomUUID().toString()
//         .substring(0, 10);
//         acc.setAccountId(accId);

//         Account a = new Account();

//         return a;

//     }

//     public List<Account> getAll() {
//         return accountRepository.findAll();
//     }

//     public Account findById(String fromAccountId) {
//         return null;
//     }

//     public void save(Account fromAccount) {
//     }

    @Service
public class AccountService {
    private List<Account> accounts = new ArrayList<>();

        public Account createAccount (Account acc) throws Exception {
        String accId= UUID.randomUUID().toString()
        .substring(0, 10);
        acc.setAccountId(accId);

        return acc;
        }

    public List<Account> findAll() {
        return accounts;
    }

    public Account findById(String accountId) {
        return accounts.stream()
                .filter(account -> account.getAccountId().equals(accountId))
                .findFirst()
                .orElse(null);
    }

    public void save(Account account) {
        // This method is not needed for the current implementation
    }
}

