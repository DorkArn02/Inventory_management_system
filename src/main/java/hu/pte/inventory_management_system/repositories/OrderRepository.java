package hu.pte.inventory_management_system.repositories;

import hu.pte.inventory_management_system.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
