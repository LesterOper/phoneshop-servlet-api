package com.es.phoneshop.Cart;

import com.es.phoneshop.exception.NotEnoughStockException;
import com.es.phoneshop.model.product.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HttpSessionCartService implements CartService {

    private static final String CART_ATTRIBUTE = "cartList";
    private static final String VIEWED_ITEMS = "viewed";
    private static CartService instance;

    private HttpSessionCartService() {
    }

    public static CartService getInstance() {
        if (instance == null) {
            synchronized (HttpSessionCartService.class) {
                if (instance == null) {
                    instance = new HttpSessionCartService();
                }
            }
        }
        return instance;
    }

    @Override
    public Cart getCart(HttpServletRequest request) {
        Cart cart;
        HttpSession session = request.getSession();
        if (session.getAttribute(CART_ATTRIBUTE) == null) {
            cart = new Cart();
            session.setAttribute(CART_ATTRIBUTE, cart);
        } else {
            cart = (Cart) session.getAttribute(CART_ATTRIBUTE);
        }
        return cart;
    }

    @Override
    public void addProduct(Cart cartList, Product product, int quantity) {
        Supplier<Stream<CartItem>> addingStream = () -> cartList.getList().stream()
                .filter(prod -> prod.getProduct().getId().equals(product.getId()));
        if (cartList.getList().stream()
                .anyMatch(prod -> prod.getProduct().getId().equals(product.getId())
                        && prod.getQuantity() + quantity <= product.getStock())) {
            addingStream.get()
                    .findFirst()
                    .ifPresent(item -> item.setQuantity(item.getQuantity() + quantity, product.getPrice()));
        } else if (cartList.getList().stream()
                .noneMatch(prod -> prod.getProduct().getId().equals(product.getId()))) {
            cartList.getList().add(new CartItem(product, quantity));
            addingStream.get()
                    .findFirst()
                    .get().setTotalCost(product.getPrice(), quantity);
        } else {
            throw new NotEnoughStockException();
        }
        cartList.setTotalCartCost(cartList.getList());
    }

    @Override
    public RecentlyViewedItems getViewedItems(HttpServletRequest request) {
        RecentlyViewedItems items;
        HttpSession session = request.getSession();
        if (session.getAttribute(VIEWED_ITEMS) == null) {
            items = new RecentlyViewedItems();
            session.setAttribute(VIEWED_ITEMS, items);
            return items;
        } else {
            items = (RecentlyViewedItems) session.getAttribute(VIEWED_ITEMS);
            return items;
        }
    }

    @Override
    public void updateCart(Cart cart, Product product, int quantity) {
        if (cart.getList().stream()
                .anyMatch(prod -> prod.getProduct().getId().equals(product.getId())
                        && quantity <= product.getStock())) {
            cart.getList().stream()
                    .filter(prod -> prod.getProduct().getId().equals(product.getId()))
                    .findFirst().ifPresent(item -> item.setQuantity(quantity, product.getPrice()));
            cart.setTotalCartCost(cart.getList());
        } else {
            throw new NotEnoughStockException();
        }
    }
}
