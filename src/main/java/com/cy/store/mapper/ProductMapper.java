package com.cy.store.mapper;

import com.cy.store.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {
    List<Product> findHotList();

    // 根据商品的id查询商品信息
    Product findById(Integer id);
}
