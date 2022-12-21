package org.selenium.pom.constants;

public enum EndPoint {
    STORE("/store"),
    ACCOUNT("/account"),
    ADD_TO_CART("/?wc-ajax=add_to_cart"),
    CHECKOUT("/checkout"),
    ACCOUNT_EDIT_BILLING_ADDRESS("/account/edit-address/billing/");

    public final String url;

    EndPoint(String url) {
        this.url = url;
    }
}
