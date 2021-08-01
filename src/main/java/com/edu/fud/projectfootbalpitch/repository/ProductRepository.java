package com.edu.fud.projectfootbalpitch.repository;

import com.edu.fud.projectfootbalpitch.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductsEntity,Long> {

    @Query(value = "select count(id) from products",nativeQuery = true)
    int TotalProduct();
    @Query(value = "select sum(o.quantity) as tongSoLuongBan from products p\n" +
            " inner join order_detail as o\n" +
            " on o.product_id = p.id where p.id=:productId group by p.id",nativeQuery = true)
    int sumQuantityProductSellByProductId(long productId);
    @Query(value = "select p.*,sum(o.quantity) as tongSoLuongBan from products p\n" +
            " inner join order_detail as o\n" +
            " on o.product_id = p.id group by p.id order by tongSoLuongBan desc limit 5",nativeQuery = true)
    List<ProductsEntity> findAllTop5ProductSell();
    @Query(value = "select sum(p.price*o.quantity) as tongSoLuongBan from products p\n" +
            " inner join order_detail as o\n" +
            " on o.product_id = p.id where p.id=:productId group by p.id",nativeQuery = true)
    double sumTotalPriceProductSellByProductId(long productId);
}
