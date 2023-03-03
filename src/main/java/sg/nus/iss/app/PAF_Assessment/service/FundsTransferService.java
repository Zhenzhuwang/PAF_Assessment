package sg.nus.iss.app.PAF_Assessment.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.app.PAF_Assessment.model.Account;
import sg.nus.iss.app.PAF_Assessment.model.Transfer;
import sg.nus.iss.app.PAF_Assessment.repository.AccountRepository;
import sg.nus.iss.app.PAF_Assessment.repository.AccountsRepository;

@Service
public class FundsTransferService {

    @Autowired
    private AccountsRepository accountsRepository;

    public Account createTransactionId (Account account) throws Exception{
        String transactionId = UUID.randomUUID().toString().substring(0, 8);
        account.setTransactionId(transactionId);

        accountsRepository.insertAccount(null);
        return account;

    }

    public boolean transferFunds(String fromAccountId, String toAccountId, BigDecimal transferAmount, String comments) {
        // Get the account details from the repository
        Optional<Account> fromAccountOpt = accountsRepository.findById(fromAccountId);
        Optional<Account> toAccountOpt = accountsRepository.findById(toAccountId);

        // Check that both accounts exist and their ids have a length of 10 characters
        if (fromAccountOpt.isEmpty() || toAccountOpt.isEmpty()) {
            return false;
        }
        Account fromAccount = fromAccountOpt.get();
        Account toAccount = toAccountOpt.get();
        if (fromAccount.getAccountId().length() != 10 || toAccount.getAccountId().length() != 10) {
            return false;
        }

        // Check that the from account is different from the to account
        if (fromAccountId.equals(toAccountId)) {
            return false;
        }

        // Check that the transfer amount is valid
        if (transferAmount.compareTo(BigDecimal.ZERO) <= 0 || transferAmount.compareTo(new BigDecimal("10")) < 0) {
            return false;
        }

        // Check that the from account has sufficient funds
        if (fromAccount.getBalance().compareTo(transferAmount) < 0) {
            return false;
        }

        // Perform the transfer
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferAmount));
        toAccount.setBalance(toAccount.getBalance().add(transferAmount));
        fromAccount.setLastTransactionDate(new Date());
        toAccount.setLastTransactionDate(new Date());
        accountsRepository.save(fromAccount);
        accountsRepository.save(toAccount);

        return true;
    }
}

        

    
