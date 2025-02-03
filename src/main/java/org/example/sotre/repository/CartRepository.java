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

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


    @Query("""
from Cart c join fetch User  u 
where u.email =:email and c.order is null
 """)
    Cart getLatestCart(@Param("email") String email);

    @Query("""
from Cart c join fetch User  u join fetch Order o
 where c.user.email =:userId
""")
    List<Cart> findCartByUserId(
            @Param("userId") String email);


    @Query(value = """
delete from cart_products c where c.cartid in(
    
    select cartid from cart as ca 
                  inner join orders as o on ca.cartid=o.orderid
                  inner join order_status as st on st.statusid=o.status_statusid
                  
  
                  where ca.userid =(
                  
                  select userid from user where email =: userId
                  )
                    and st.statusname='OPEN'
)
""",nativeQuery = true)
    @Modifying
    int deleteProduct(String userId, int productId);


}



