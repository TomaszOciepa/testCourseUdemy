package com.tom.testcourse.account;

import com.tom.testcourse.account.Account;
import com.tom.testcourse.account.Address;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AccountTest {

    @Test
    public void newAccountShouldBeNotActiveAfterCreation(){
        //given
        //when
        Account newAccount = new Account();

        //then
        assertFalse(newAccount.isActive());

//        Hamcrest
//        assertThat(newAccount.isActive(), equalTo(false));
//        assertThat(newAccount.isActive(), is(false));

//        asertj
        assertThat(newAccount.isActive()).isFalse();
    }

    @Test
    public void accountShouldBeActiveAfterActivatio(){
        //given
        Account newAccount = new Account();

        //when
        newAccount.activate();

        //then

//       Hamcrest
//        assertTrue(newAccount.isActive());
//        assertThat(newAccount.isActive(), equalTo(true));

//        asertJ
        assertThat(newAccount.isActive()).isTrue();
    }

    @Test
    void newlyCreatedAccountShouldNotHaveDefaultDeliveryAddressSet(){
        //given
        Account account = new Account();

        //when
        Address address = account.getDefaultDeliveryAddress();

        //then
//        hamcrest
//        assertNull(address);
//        assertThat(address, nullValue());

//        assertJ
        assertThat(address).isNull();
    }


    @Test
    void defaultDeliveryAddressShouldNotBeNullAfterBeingSet(){
        //given
        Address address = new Address("Grunwaldzka", "100");
        Account account = new Account();
        account.setDefaultDeliveryAddress(address);

        //when
        Address defaultAddress = account.getDefaultDeliveryAddress();

        //then
//        hamcrest
//        assertNotNull(defaultAddress);
//        assertThat(defaultAddress, notNullValue());

//        assertJ
        assertThat(defaultAddress).isNotNull();
    }

    @RepeatedTest(5)
    @Test
    void newAccountWithNotNullAddressShouldBeActive(){
        //given
        Address address = new Address("Grunwaldzka", "200/6");

        //when
        Account account = new Account(address);

        //then
        assumingThat(address != null, ()-> {
            assertTrue(account.isActive());
        });
    }
}
