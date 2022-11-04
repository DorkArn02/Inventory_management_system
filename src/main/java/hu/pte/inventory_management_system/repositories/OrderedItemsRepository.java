package hu.pte.inventory_management_system.repositories;

import hu.pte.inventory_management_system.models.OrderedItems;
import hu.pte.inventory_management_system.models.OrderedItemsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedItemsRepository extends JpaRepository<OrderedItems, OrderedItemsId> {
}
