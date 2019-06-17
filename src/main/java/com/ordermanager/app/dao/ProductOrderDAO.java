package com.ordermanager.app.dao;

import com.ordermanager.app.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderDAO extends JpaRepository<ProductOrder, Integer> {


}
