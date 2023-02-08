package com.tom.testcourse.cart;

import com.tom.testcourse.order.Order;
import com.tom.testcourse.order.OrderStatus;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class CartServiceTest {

    @Test
    void processCartShouldSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler).sendToPrepare(cart); // sprawdza czy dana metoda została wywołana
        then(cartHandler).should().sendToPrepare(cart); //analogiczny zapis jak wyżej tylko zgodny z BDD Mockito

        verify(cartHandler, times(1)).sendToPrepare(cart); // times sprawdza ile razy została wywołana dana metoda.
        verify(cartHandler, atLeastOnce()).sendToPrepare(cart); // atLeastOnce sprawdza czy metoda została wywołana 1 raz

        //sprawdzenie kolejności wywołania metod
        InOrder inOrder = inOrder(cartHandler);
        inOrder.verify(cartHandler).canHandleCart(cart);
        inOrder.verify(cartHandler).sendToPrepare(cart);

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void processCartShouldNotSendToPrepare() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(cart); // sprawdza czy dana metoda została wywołana
        then(cartHandler).should(never()).sendToPrepare(cart);
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void processCartShouldNotSendToPrepareWithArgumentsMatchers() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(false);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        verify(cartHandler, never()).sendToPrepare(any(Cart.class)); // sprawdza czy dana metoda została wywołana
        then(cartHandler).should(never()).sendToPrepare(any(Cart.class));
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.REJECTED));
    }

    @Test
    void canHandleCartShouldReturnMultipleValues() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);


        given(cartHandler.canHandleCart(any(Cart.class))).willReturn(true, false, false, true);

        //then
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(false));
        assertThat(cartHandler.canHandleCart(cart), equalTo(true));

    }

    @Test
    void processCartShouldSendToPrepareWithLambdas() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(argThat(c -> c.getOrders().size() > 0))).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
        then(cartHandler).should().sendToPrepare(cart); //analogiczny zapis jak wyżej tylko zgodny z BDD Mockito
        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }

    @Test
    void canHandleCartShouldThrowException() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        given(cartHandler.canHandleCart(cart)).willThrow(IllegalStateException.class);

        //when
        //then
        assertThrows(IllegalStateException.class, ()->cartService.processCart(cart));

    }

    @Test
    void processCartShouldSendToPrepareWithArgumentCaptor() {
        //given
        Order order = new Order();
        Cart cart = new Cart();
        cart.addOrderToCart(order);

        CartHandler cartHandler = mock(CartHandler.class);
        CartService cartService = new CartService(cartHandler);

        ArgumentCaptor<Cart> argumentCaptor = ArgumentCaptor.forClass(Cart.class);

        given(cartHandler.canHandleCart(cart)).willReturn(true);

        //when
        Cart resultCart = cartService.processCart(cart);

        //then
//        verify(cartHandler).sendToPrepare(argumentCaptor.capture());
        then(cartHandler).should().sendToPrepare(argumentCaptor.capture()); //analogiczny zapis jak wyżej tylko zgodny z BDD Mockito
        assertThat(argumentCaptor.getValue().getOrders().size(), equalTo(1));

        assertThat(resultCart.getOrders(), hasSize(1));
        assertThat(resultCart.getOrders().get(0).getOrderStatus(), equalTo(OrderStatus.PREPARING));
    }
}