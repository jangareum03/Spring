package com.example.foodList.wishlist.sevice;

import com.example.foodList.wishlist.service.WishListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListServiceTest {

    @Autowired
    private WishListService wishListService;

    @Test
    public void searchTest(){
        var result = wishListService.search("초밥");

        System.out.println(result);

        Assertions.assertNotNull(result);
    }
}
