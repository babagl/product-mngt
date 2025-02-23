package com.babagl.ecommerce.order;

import com.babagl.ecommerce.customer.CustomerClient;
import com.babagl.ecommerce.customer.CustomerResponse;
import com.babagl.ecommerce.exception.BusinessException;
import com.babagl.ecommerce.kafka.OrderConfirmation;
import com.babagl.ecommerce.kafka.OrderProducer;
import com.babagl.ecommerce.orderline.OrderLineRequest;
import com.babagl.ecommerce.orderline.OrderLineService;
import com.babagl.ecommerce.product.ProductClient;
import com.babagl.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    public Integer createOrder(OrderRequest request) {
        //check the costumer --> OpenFeign;
        CustomerResponse customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(()-> new BusinessException("On ne peut pas creer une commande:: Le client dont l'ID est egale a "+request.customerId()+ "n'existe pas"));

        //purchase the products --> product-ms(RestTemplate)
        var purchasedProduct = this.productClient.purchaseProducts(request.products());


        //persist order
        var order = this.repository.save(mapper.toOrder(request));

        //persist order lines

        for (PurchaseRequest purchaseRequest : request.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        //todo start payment process

        //send the order confirmation

        //send the order confirmation  --> notification-ms (kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProduct

                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(()->new EntityNotFoundException(String.format("L'order  avec l'id " + orderId + " n'existe pas")));
    }
}
