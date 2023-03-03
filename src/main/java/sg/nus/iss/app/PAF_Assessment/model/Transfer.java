package sg.nus.iss.app.PAF_Assessment.model;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Transfer {

    
    
    @NotNull(message = "Account should exist")
    @Size(min=10, message = "The length of the account Id should be 10 characters")
    private String fromAccountId;

    @NotNull(message = "Account should exist")
    private String toAccountId;

    @NotNull
    @DecimalMin("10.00")
    private BigDecimal amount;

    private String comments;

    @Id
    private String transactionId;
    
    private String date;

    public String getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(String fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(String toAccountId) {
        this.toAccountId = toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Transfer() {
    }

   

    

   


    
}
