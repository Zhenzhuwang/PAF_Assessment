package sg.nus.iss.app.PAF_Assessment.controller;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sg.nus.iss.app.PAF_Assessment.model.Account;
import sg.nus.iss.app.PAF_Assessment.model.Transfer;
import sg.nus.iss.app.PAF_Assessment.repository.AccountRepository;
import sg.nus.iss.app.PAF_Assessment.service.AccountService;

@Controller
public class FundsTransferController {

    @Autowired
    private AccountService accountService;

    // @GetMapping("/")
    // public String landingPage(Model model) {
    //     model.addAttribute("transfer", new Transfer());
    //     model.addAttribute("accounts", accountService.findAll());
    //     return "landing";
    // }

    // @PostMapping("/transfer")
    public String transferFunds(@Valid @ModelAttribute("transfer") Transfer transfer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }

        // Check 1 - Both accounts exist
        Account fromAccountOptional = accountService.findById(transfer.getFromAccountId());
        Account toAccountOptional = accountService.findById(transfer.getToAccountId());
        if (fromAccountOptional.isEmpty() || toAccountOptional.isEmpty()) {
            model.addAttribute("transferError", "One or more accounts do not exist.");
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }
        Account fromAccount = fromAccountOptional.get();
        Account toAccount = toAccountOptional.get();

        // Check 2 - Account ID length is 10 characters
        if (transfer.getFromAccountId().length() != 10 || transfer.getToAccountId().length() != 10) {
            model.addAttribute("transferError", "One or more account IDs have invalid length.");
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }

        // Check 3 - From and To accounts must be different
        if (fromAccount.equals(toAccount)) {
            model.addAttribute("transferError", "From and To accounts cannot be the same.");
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }

        // Check 4 - Transfer amount cannot be 0 or negative
        if (transfer.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            model.addAttribute("transferError", "Transfer amount must be greater than 0.");
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }

        // Check 5 - Minimum transfer amount is $10
        if (transfer.getAmount().compareTo(BigDecimal.TEN) < 0) {
            model.addAttribute("transferError", "Minimum transfer amount is $10.");
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }

        // Check 6 - From account should have sufficient funds
        if (fromAccount.getBalance().compareTo(transfer.getAmount()) < 0) {
            model.addAttribute("transferError", "Insufficient funds in the From account.");
            model.addAttribute("accounts", accountService.findAll());
            return "landing";
        }

        // Perform the transfer
        fromAccount.setBalance(fromAccount.getBalance().subtract(transfer.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transfer.getAmount()));
        accountService.save(fromAccount);
        accountService.save(toAccount);

        // Display success message
        model.addAttribute("transferSuccess", "Funds transferred successfully.");
        model.addAttribute("accounts", accountService.findAll());
        return "landing";
    }

}
