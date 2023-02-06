package com.tom.testcourse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


class CartTest {

//    @Disabled
    @Test
    @DisplayName("Cart is able to process 1000 orders in 10 ms")
    void simulateLargeOrder(){
        //given
        Cart cart = new Cart();

        //When
        //Then
        assertTimeout(Duration.ofMillis(10 ), cart::simulateLargeOrder);

    }

}