package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.dtos.TransactionResponseDTO;
import hu.pte.inventory_management_system.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public List<TransactionResponseDTO> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable Integer id){
        return new ResponseEntity<>(transactionService.getTransactionById(id), HttpStatus.OK);
    }
}
