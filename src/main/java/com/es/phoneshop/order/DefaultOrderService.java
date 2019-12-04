package com.es.phoneshop.order;

import com.es.phoneshop.Cart.Cart;
import com.es.phoneshop.Cart.CartItem;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

public class DefaultOrderService implements OrderService{
    private static OrderService orderService;

    private DefaultOrderService() {
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            synchronized (OrderService.class) {
                if (orderService == null) {
                    orderService = new DefaultOrderService();
                }
            }
        }
        return orderService;
    }

    @Override
    public Order getOrder(Cart cart) {
        Order order = new Order(cart.getList().stream()
                .map(CartItem::new)
                .collect(Collectors.toList())
        );

        order.setSubtotal(cart.getTotalCartCost());
        order.setDeliveryCost(getDeliveryCost());
        order.setTotalCartCost(cart.getList(), order.getDeliveryCost());
        order.setTotalCartQuantity(cart.getList());
        return order;
    }

    @Override
    public String placeOrder(Order order) {
        String secureId = UUID.randomUUID().toString();
        order.setSecureId(secureId);
        ArrayListOrderDao.getInstance().saveOrder(order);
        return secureId;
    }

    @Override
    public Order getOrder(String secureId) {
        return ArrayListOrderDao.getInstance().getOrder(secureId);
    }

    public BigDecimal getDeliveryCost() {
        return new BigDecimal("5");
    }
}
