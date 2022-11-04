package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.OrderedItems;
import hu.pte.inventory_management_system.models.Orders;
import hu.pte.inventory_management_system.models.OrdersDTO;
import hu.pte.inventory_management_system.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public ResponseEntity<Orders> createNewOrder(@RequestBody Orders orders){
        return new ResponseEntity<>(orderService.createNewOrder(orders), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<OrdersDTO> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable Integer id){
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Integer id){
        orderService.deleteOrderById(id);
    }

    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<OrderedItems> addProductToOrder(@PathVariable Integer orderId,
                                                          @PathVariable Integer productId,
                                                          @RequestBody Integer quantity){
        return new ResponseEntity<>(orderService.addProductToOrder(productId, orderId, quantity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{orderId}/{productId}")
    public void deleteProductFromOrder(@PathVariable Integer orderId,
                                                          @PathVariable Integer productId){
        orderService.deleteProductFromOrder(productId, orderId);
    }
}
