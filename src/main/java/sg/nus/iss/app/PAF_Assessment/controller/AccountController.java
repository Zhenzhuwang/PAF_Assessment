package sg.nus.iss.app.PAF_Assessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import sg.nus.iss.app.PAF_Assessment.model.Account;
import sg.nus.iss.app.PAF_Assessment.model.Transfer;
import sg.nus.iss.app.PAF_Assessment.service.AccountService;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String landing(Model model) {
        List<Account> accounts = accountService.findAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("transfer", new Transfer());
        return "landing";
    }

    @PostMapping("/transfer")
    public String transfer(@ModelAttribute("transfer") @Valid Transfer transfer, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<Account> accounts = accountService.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("errorMessage", "Invalid transfer details");
            return "landing";
        }

        Account fromAccount = accountService.findById(transfer.getFromAccountId());
        Account toAccount = accountService.findById(transfer.getToAccountId());

        if (fromAccount == null || toAccount == null) {
            List<Account> accounts = accountService.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("errorMessage", "Invalid account selected");
            return "landing";
        }

        if (fromAccount.getBalance().compareTo(transfer.getAmount()) < 0) {
            List<Account> accounts = accountService.findAll();
            model.addAttribute("accounts", accounts);
            model.addAttribute("errorMessage", "Insufficient funds in source account");
            return "landing";
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transfer.getAmount()));
        toAccount.setBalance(toAccount.getBalance().add(transfer.getAmount()));

        accountService.save(fromAccount);
        accountService.save(toAccount);

        return "redirect:/";
    }
}
