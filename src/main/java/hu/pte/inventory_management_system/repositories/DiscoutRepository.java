package hu.pte.inventory_management_system.repositories;

import hu.pte.inventory_management_system.models.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoutRepository extends JpaRepository<Discount, Integer> {
}
