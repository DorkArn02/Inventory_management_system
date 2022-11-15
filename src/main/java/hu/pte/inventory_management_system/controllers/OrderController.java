package hu.pte.inventory_management_system.controllers;

import hu.pte.inventory_management_system.models.OrderedItems;
import hu.pte.inventory_management_system.models.dtos.OrderRequestDTO;
import hu.pte.inventory_management_system.models.dtos.OrdersDTO;
import hu.pte.inventory_management_system.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Validated
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public ResponseEntity<OrderRequestDTO> createNewOrder(@RequestBody @Valid OrderRequestDTO orders){
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

    @PutMapping("/{orderId}")
    public void changeOrderStatus(@PathVariable Integer orderId, @RequestBody Boolean status){
        orderService.changeOrderStatus(orderId, status);
    }
}
