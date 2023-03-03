package sg.nus.iss.app.PAF_Assessment.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.app.PAF_Assessment.model.Account;
import sg.nus.iss.app.PAF_Assessment.repository.AccountsRepository;

@Service
public class LogAuditService {
    
    @Autowired
    private AccountsRepository accountsRepository;
    
    public void logTransaction(String transactionId, String date, String fromAccount, String toAccount, BigDecimal amount) {
        Account account = new Account();
        accountsRepository.save(account);
    }
    
}

    

