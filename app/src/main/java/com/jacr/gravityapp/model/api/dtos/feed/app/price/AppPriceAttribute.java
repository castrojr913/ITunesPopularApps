package com.jacr.gravityapp.model.api.dtos.feed.app.price;

import java.io.Serializable;

/**
 * Created by Jesus on 11/21/2015.
 */
public class AppPriceAttribute implements Serializable {

    private String amount;
    private String currency;

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

}
