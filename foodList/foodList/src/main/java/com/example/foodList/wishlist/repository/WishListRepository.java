package com.example.foodList.wishlist.repository;

import com.example.foodList.db.MemoryDbRepositoryAbstract;
import com.example.foodList.wishlist.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositoryAbstract<WishListEntity> {
}
