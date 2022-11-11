package hu.pte.inventory_management_system.services.interfaces;

import hu.pte.inventory_management_system.dtos.OrdersDTO;
import hu.pte.inventory_management_system.models.OrderedItems;
import hu.pte.inventory_management_system.models.Orders;

import java.util.List;

public interface IOrderService {
    Orders createNewOrder(Orders orders);
    List<OrdersDTO> getAllOrders();
    OrdersDTO getOrderById(Integer id);
    void deleteOrderById(Integer id);
    OrderedItems addProductToOrder(Integer productId, Integer orderId, Integer quantity);
    void deleteProductFromOrder(Integer productId, Integer orderId);
    void changeOrderStatus(Integer orderId, Boolean status);
}
