package com.tom.testcourse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
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

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 19})
    void mealPricesShouldBeLowerThan20(int price){
        assertThat(price, lessThan(20));
    }

    @ParameterizedTest
    @MethodSource("createMealsWithNameAndPrice")
    void burgersShouldHaveCorrectNameAndPrice(String name, int price){
        assertThat(name, containsString("burger"));
        assertThat(price, greaterThanOrEqualTo(10));
    }

    private static Stream<Arguments> createMealsWithNameAndPrice(){
        return Stream.of(
                Arguments.of("Hamburger", 10),
                Arguments.of("Cheaseburger", 12)
        );
    }

    @ParameterizedTest
    @MethodSource("createCakeNames")
    void cakeNamesShouldEndWithCake(String name){
        assertThat(name, notNullValue());
        assertThat(name, endsWith("cake"));
    }

    private static Stream<String> createCakeNames(){
        List<String> cakeNames = Arrays.asList("cheeasecake", "Fruitcake", "Cupcake");
        return cakeNames.stream();
    }
}