package com.ordermanager.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="product_order")
public class ProductOrder {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min=1, max=100, message = "Size cannot be greater than 100 Characters")
    @Column(name="name_order")
    private String nameOrder;
    @NotNull
    private Integer amount;
    private Integer discount;

    public ProductOrder(){}

    public ProductOrder(String nameOrder, Integer amount, Integer discount) {
        this.nameOrder = nameOrder;
        this.amount = amount;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
