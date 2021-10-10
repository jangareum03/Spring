package com.example.foodList.wishlist.repository;

import com.example.foodList.wishlist.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create(){
        var wishList = new WishListEntity();
        wishList.setTitle("title");
        wishList.setCategory("category");
        wishList.setAddress("address");
        wishList.setRoadAddress("readAddress");
        wishList.setHomePageLink("");
        wishList.setImageLink("");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    public void saveTest(){
        var wishList = create();
        var result = wishListRepository.save(wishList);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getIndex());

    }

    @Test
    public void updateTest(){
        var wishList = create();
        var result = wishListRepository.save(wishList);

        result.setTitle("update test");
        var save = wishListRepository.save(result);

        Assertions.assertEquals("update test", save.getTitle());
        Assertions.assertEquals(1, wishListRepository.findAll().size());
    }

    @Test
    public void findByIdTest(){
        var wishList = create();
        wishListRepository.save(wishList);

        var result = wishListRepository.findById(1);

        Assertions.assertEquals(true, result.isPresent());
        Assertions.assertEquals(1, result.get().getIndex());
    }

    @Test
    public void deleteTest(){
        var wishList = create();
        wishListRepository.save(wishList);

        wishListRepository.deleteByID(1);

        int count = wishListRepository.findAll().size();

        Assertions.assertEquals(0, count);
    }

    @Test
    public void listAllTest(){
        var wishList1 = create();
        wishListRepository.save(wishList1);

        var wishList2 = create();
        wishListRepository.save(wishList2);

        int count = wishListRepository.findAll().size();

        Assertions.assertEquals(2, count);
    }
}
