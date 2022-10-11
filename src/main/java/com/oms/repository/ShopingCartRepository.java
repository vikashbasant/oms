package com.oms.repository;

import com.oms.entity.ShopingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopingCartRepository extends JpaRepository<ShopingCart, Integer> {
}
