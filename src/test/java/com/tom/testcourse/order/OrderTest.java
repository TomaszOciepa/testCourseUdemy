package com.tom.testcourse.order;

import com.tom.testcourse.meal.Meal;
import com.tom.testcourse.extensions.BeforeAfterExtension;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(BeforeAfterExtension.class)
class OrderTest {

    private Order order;

    @BeforeEach
    void initializeOrder(){
        System.out.println("@BeforeEach method");
        order = new Order();
    }

    @AfterEach
    void cleanUp(){
        System.out.println("@AfterEach method");
        order.cancel();
    }
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
//        //given
//        Order order = new Order();

        //then
        assertThat(order.getMeals(), empty());
        assertThat(order.getMeals().size(), equalTo(0));
        assertThat(order.getMeals(), hasSize(0));
        MatcherAssert.assertThat(order.getMeals(), emptyCollectionOf(Meal.class));
    }

    @Test
    void addingMealToOrderShouldIncreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "Burger Xl");
        Meal meal2 = new Meal(10, "Zapiekana");
//        Order order = new Order();

        //when
        order.addMealToOrder(meal);

        //then
        assertThat(order.getMeals(), hasSize(1));
        assertThat(order.getMeals(), contains(meal));
        assertThat(order.getMeals(), hasItem(meal));

        assertThat(order.getMeals().get(0).getPrice(), equalTo(20));

    }
    @Tag("burger")
    @Test
    void removingMealFromOrderShouldDecreaseOrderSize(){
        //given
        Meal meal = new Meal(20, "Burger Xl");
//        Order order = new Order();

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
//        Order order = new Order();

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


}