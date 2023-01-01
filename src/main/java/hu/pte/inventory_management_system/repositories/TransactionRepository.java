package hu.pte.inventory_management_system.repositories;

import hu.pte.inventory_management_system.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
