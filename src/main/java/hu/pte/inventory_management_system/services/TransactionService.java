package hu.pte.inventory_management_system.services;

import hu.pte.inventory_management_system.models.Transaction;
import hu.pte.inventory_management_system.models.dtos.TransactionResponseDTO;
import hu.pte.inventory_management_system.repositories.TransactionRepository;
import hu.pte.inventory_management_system.services.interfaces.ITransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<TransactionResponseDTO> getTransactions() {
        List<TransactionResponseDTO> l = new ArrayList<>();
        transactionRepository.findAll().forEach(i -> l.add(new TransactionResponseDTO(i)));
        return l;
    }

    @Override
    public TransactionResponseDTO getTransactionById(Integer id) {
        if(transactionRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new TransactionResponseDTO(transactionRepository.findById(id).get());
    }
}
