package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.dtos.TransactionResponseDTO;

import java.util.List;

public interface ITransactionService {
    List<TransactionResponseDTO> getTransactions();
    TransactionResponseDTO getTransactionById(Integer id);
}
