package sg.nus.iss.app.PAF_Assessment.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {
    
    @Size(min=10, message = "The length of the account Id should be 10 characters")
    @NotNull (message = "The bank account cannot be null")
    @Id
    private String accountId;

    @NotNull (message = "The account name cannot be null")
    private String name;

    private BigDecimal balance;

    @Size(max=8)
    private String transactionId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account get() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setLastTransactionDate(Date date) {
    }
    
    
}
