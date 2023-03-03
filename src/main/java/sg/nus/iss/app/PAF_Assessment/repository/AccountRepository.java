package sg.nus.iss.app.PAF_Assessment.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import sg.nus.iss.app.PAF_Assessment.model.Account;
import sg.nus.iss.app.PAF_Assessment.model.Transfer;

import static sg.nus.iss.app.PAF_Assessment.repository.Queries.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    
    @Autowired
    private JdbcTemplate template;

    @Bean
    public CommandLineRunner loadData(AccountRepository accountRepository) {
    return args -> {
        // Load data from data.csv file
        InputStream inputStream = getClass().getResourceAsStream("/data.csv");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        CsvToBean<Account> csvToBean = new CsvToBeanBuilder<Account>(reader)
                .withType(Account.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        List<Account> accounts = csvToBean.parse();

        // Save data to database
        accountRepository.saveAll(accounts);
    };
}

    private void saveAll(List<Account> accounts) {
    }

    public boolean insertAccount(Account account){
        return template.update(SQL_INSERT_ACCOUNT_TABLE, 
                account.getAccountId(),
                account.getName(), 
                account.getBalance() ) > 0;
    }

    public int getAccountByAccId(){
        return 0; 
    }

    public Optional<Account> findById(String fromAccount) {
        return null;
    }

}
