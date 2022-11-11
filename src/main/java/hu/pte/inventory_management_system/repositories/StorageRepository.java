package hu.pte.inventory_management_system.repositories;


import hu.pte.inventory_management_system.models.Storage;
import hu.pte.inventory_management_system.models.StorageId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StorageRepository extends JpaRepository<Storage, StorageId> {
}
