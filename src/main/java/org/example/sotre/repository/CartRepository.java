package org.example.sotre.repository;

import org.example.sotre.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Transactional

    @Modifying
    @Query("DELETE CartProduct cp WHERE cp.product.productID = :id")
    void detachProductFromCart(@Param("id") int id);

    @Query("""
from Cart c join fetch c.user u join fetch c.cartProducts where u.email =:email and c.order is null
 """)
    Cart getLatestCart(@Param("email") String email);

    @Query("""
from Cart c join fetch c.user  u join fetch Order o
 where c.user.email =:userId
""")
    List<Cart> findCartByUserId(
            @Param("userId") String email);


    @Query(value = """
delete from cart_products c where c.cart_id in(
    

    select ca.cartid from cart as ca
                 left  join orders as o on ca.cartid=o.cartid
   					where o.orderid is null
       
                  and ca.userid in (
                  
                  select userid from user where email =:userId
                  )
) and c.product_id=:productId
""",nativeQuery = true)
    @Modifying
    int deleteProduct(String userId, int productId);


}



