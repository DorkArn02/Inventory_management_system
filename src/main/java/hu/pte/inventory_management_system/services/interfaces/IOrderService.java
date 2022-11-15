package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.models.OrderedItems;
import hu.pte.inventory_management_system.models.dtos.OrderRequestDTO;
import hu.pte.inventory_management_system.models.dtos.OrdersDTO;

import java.util.List;

public interface IOrderService {
    OrderRequestDTO createNewOrder(OrderRequestDTO orders);
    List<OrdersDTO> getAllOrders();
    OrdersDTO getOrderById(Integer id);
    void deleteOrderById(Integer id);
    OrderedItems addProductToOrder(Integer productId, Integer orderId, Integer quantity);
    void deleteProductFromOrder(Integer productId, Integer orderId);
    void changeOrderStatus(Integer orderId, Boolean status);
}
