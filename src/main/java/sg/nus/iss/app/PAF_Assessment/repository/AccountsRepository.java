package sg.nus.iss.app.PAF_Assessment.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import sg.nus.iss.app.PAF_Assessment.model.Account;

@Repository
public class AccountsRepository {

    public Optional<Account> findById(String toAccountId) {
        return null;
    }

    public void insertAccount(Object object) {
    }

    public void save(Account fromAccount) {
    }
    
}
