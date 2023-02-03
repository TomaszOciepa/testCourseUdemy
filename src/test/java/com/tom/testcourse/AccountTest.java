package com.tom.testcourse;

import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

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
}
