package com.tom.testcourse;

import org.junit.jupiter.api.Test;

//import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class MealTest {

    @Test
    void shouldReturnDiscountedPrice() {
        //given
        Meal meal = new Meal(35);

        //when
        int discountedPrice = meal.getDiscountedPrice(7);

        //then
        assertEquals(28, discountedPrice);

//        Hamcrest
//        assertThat(discountedPrice, equalTo(28));
//        assertJ
        assertThat(discountedPrice).isEqualTo(28);
    }

    @Test
    void referencesToTheSameObjectShouldBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = meal1;

        //then
        assertSame(meal1, meal2);
//        hamcrest
//        assertThat(meal1, sameInstance(meal2));

//        assertJ
        assertThat(meal1).isSameAs(meal2);
    }

    @Test
    void referencesToTheSameObjectShouldNotBeEqual(){
        //given
        Meal meal1 = new Meal(10);
        Meal meal2 = new Meal(12);

        //then
        assertNotSame(meal1, meal2);
//        Hamcrest
//        assertThat(meal1, not(sameInstance(meal2)));

//        assertJ
        assertThat(meal1).isNotSameAs(meal2);
    }


    @Test
    void twoMealsShouldBeEqualWhenPriceAndNameAreTheSame(){
        //given
        Meal meal1 = new Meal(10, "pizza");
        Meal meal2 = new Meal(10, "pizza");

        //then
        assertEquals(meal1, meal2);

//        assertJ
        assertThat(meal1).isEqualTo(meal2);
    }

    @Test
    void exceptionShouldBeThrowIfDiscountIsHigherThanThePrice(){
        //given
        Meal meal = new Meal(8, "Soup");

        //then
        assertThrows(IllegalArgumentException.class, ()-> meal.getDiscountedPrice(10));
    }
}