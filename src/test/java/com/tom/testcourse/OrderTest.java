package com.tom.testcourse;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


class OrderTest {

    @Test
    void testAssertArrayEquals(){
        //given
        int[] ints1 = {1,2,3};
        int[] ints2 = {1,2,3};

        //then
        assertArrayEquals(ints1, ints2);
    }


    @Test
    void mealListShouldBeEmptyAfterCreationOfOrder(){
        //given
        Order order = new Order();

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "Burger Xl");
        Meal meal2 = new Meal(10, "Zapiekana");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(), equalTo(20));

    }

    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "Burger Xl");
        Order order = new Order();

        //when
        order.addMealToOrder(meal);
        order.removeMealFromOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(0));
        assertThat(order.getMeals(), not(contains(meal)));
    }

    @Test
    void mealsShouldBeInCorrectOrderAfterAddingThemToOrder(){
        //given
        Meal meal1 = new Meal(20, "Burger Xl");
        Meal meal2 = new Meal(10, "Zapiekana");
        Order order = new Order();

        //when
        order.addMealToOrder(meal1);
        order.addMealToOrder(meal2);

        //then
        assertThat(order.getMeals(), contains(meal1, meal2));
        assertThat(order.getMeals(), containsInAnyOrder(meal2, meal1));
    }

    @Test
    void testIfTwoMealListsAreTheSame(){
        //given
        Meal meal1 = new Meal(20, "Burger Xl");
        Meal meal2 = new Meal(10, "Zapiekana");
        Meal meal3 = new Meal(12, "Fish and Chips");

        List<Meal> mealsList1 = Arrays.asList(meal1, meal2);
        List<Meal> mealsList2 = Arrays.asList(meal1, meal2);

        //then
        assertThat(mealsList1, is(mealsList2));
    }

    @Test
    void addMealToOrder() {
    }

    @Test
    void removeMealFromOrder() {
    }

    @Test
    void getMeals() {
    }
}