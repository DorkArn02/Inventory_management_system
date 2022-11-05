package hu.pte.inventory_management_system.services;

import hu.pte.inventory_management_system.models.*;
import hu.pte.inventory_management_system.repositories.OrderRepository;
import hu.pte.inventory_management_system.repositories.OrderedItemsRepository;
import hu.pte.inventory_management_system.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderedItemsRepository orderedItemsRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, OrderedItemsRepository orderedItemsRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderedItemsRepository = orderedItemsRepository;
        this.productRepository = productRepository;
    }

    /**
     * Create a new order
     * @param orders RequestBody order
     * @return Order
     */
    public Orders createNewOrder(Orders orders){
        orders.setCreated(LocalDateTime.now());
        orders.setCompleted(false);
        return orderRepository.save(orders);
    }

    /**
     * Get list of orders
     * @return List of orders
     */
    public List<OrdersDTO> getAllOrders(){
        List<OrdersDTO> ordersDTOS = new ArrayList<>();
        orderRepository.findAll().forEach(orders -> {
            List<OrderedItemsDTO> orderedItemsDTOS = new ArrayList<>();
            orders.getOrderedItemsList().forEach(orderedItems -> {
                Product p = orderedItems.getOrderedItemsId().getProduct();
                orderedItemsDTOS.add(OrderedItemsDTO.builder().name(p.getName())
                        .product_id(p.getId())
                        .price(p.getPrice())
                        .quantity(orderedItems.getQuantity())
                        .description(p.getDescription()).thumbnail(p.getThumbnail())
                        .total_price(p.getPrice()*orderedItems.getQuantity()).build());
            });
            ordersDTOS.add(OrdersDTO.builder()
                    .order_id(orders.getId())
                    .begin(orders.getCreated())
                    .expectedDelivery(orders.getExpectedDelivery())
                    .completed(orders.getCompleted())
                    .orderedItemsDTOS(orderedItemsDTOS)
                    .build());
        });
       return ordersDTOS;
    }

    /**
     * Get a specified order
     * @param id PathVariable id
     * @return Order
     */
    public OrdersDTO getOrderById(Integer id){
        if(orderRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Orders orders = orderRepository.findById(id).get();

        List<OrderedItemsDTO> orderedItemsDTOS = new ArrayList<>();

        orders.getOrderedItemsList().forEach(orderedItems -> {
           Product p = orderedItems.getOrderedItemsId().getProduct();
           orderedItemsDTOS.add(OrderedItemsDTO.builder().name(p.getName())
                   .product_id(p.getId())
                   .price(p.getPrice())
                   .quantity(orderedItems.getQuantity())
                   .description(p.getDescription()).thumbnail(p.getThumbnail())
                   .total_price(p.getPrice()*orderedItems.getQuantity()).build());
        });

        return OrdersDTO.builder()
                .order_id(orders.getId())
                .begin(orders.getCreated())
                .expectedDelivery(orders.getExpectedDelivery())
                .orderedItemsDTOS(orderedItemsDTOS)
                .completed(orders.getCompleted())
                .build();
    }

    /**
     * Deletes a specified order
     * @param id PathVariable id
     */
    public void deleteOrderById(Integer id){
        if(orderRepository.findById(id).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        orderRepository.deleteById(id);
    }

    /**
     * Add a product to a specified order
     * @param productId PathVariable product id
     * @param orderId PathVariable order id
     * @param quantity RequestBody quantity
     * @return OrderedItems
     */
    public OrderedItems addProductToOrder(Integer productId, Integer orderId, Integer quantity){
        if(orderRepository.findById(orderId).isEmpty() ||
        productRepository.findById(productId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        OrderedItemsId orderedItemsId = new OrderedItemsId();
        orderedItemsId.setOrders(orderRepository.findById(orderId).get());
        orderedItemsId.setProduct(productRepository.findById(productId).get());
        OrderedItems orderedItems = new OrderedItems();

        orderedItems.setOrderedItemsId(orderedItemsId);
        orderedItems.setQuantity(quantity);
       return orderedItemsRepository.save(orderedItems);
    }

    /**
     * Deletes a product from a specified order
     * @param productId PathVariable Product id
     * @param orderId PathVariable Order id
     */
    public void deleteProductFromOrder(Integer productId, Integer orderId){
        if(orderRepository.findById(orderId).isEmpty() ||
                productRepository.findById(productId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if(orderedItemsRepository.findById(new OrderedItemsId(orderRepository.findById(orderId).get(), productRepository.findById(productId).get())).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        orderedItemsRepository.deleteById(new OrderedItemsId(orderRepository.findById(orderId).get(), productRepository.findById(productId).get()));
    }

    /**
     * Changes the status of the order
     * @param orderId PathVariable Order id
     * @param status Boolean
     */
    public void changeOrderStatus(Integer orderId, Boolean status){
        if(orderRepository.findById(orderId).isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Orders orders = orderRepository.findById(orderId).get();
        orders.setCompleted(status);
        orderRepository.save(orders);
    }
}
