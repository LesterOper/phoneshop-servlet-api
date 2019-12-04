package com.es.phoneshop.order;

import java.util.ArrayList;
import java.util.List;

public class ArrayListOrderDao implements OrderDAO {

    private static OrderDAO orderDAO;

    private List<Order> orderList;

    private ArrayListOrderDao() {
        orderList = new ArrayList<>();
    }

    public static OrderDAO getInstance() {
        if (orderDAO == null) {
            synchronized (ArrayListOrderDao.class) {
                if (orderDAO == null) {
                    orderDAO = new ArrayListOrderDao();
                }
            }
        }
        return orderDAO;
    }

    @Override
    public Order getOrder(String secureId) {
        return orderList.stream()
                .filter(order -> order.getSecureId().equals(secureId))
                .findFirst()
                .get();
    }

    @Override
    public void saveOrder(Order order) {
        if (orderList.contains(order)) {
            throw new IllegalArgumentException();
        }
        if (order != null) {
            orderList.add(order);
        }
    }
}
