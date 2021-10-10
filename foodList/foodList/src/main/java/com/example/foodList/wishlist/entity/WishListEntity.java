package com.example.foodList.wishlist.entity;

import com.example.foodList.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WishListEntity extends MemoryDbEntity {

    private String title;                       //음식명, 장소명
    private String category;                //카테고리
    private String address;                 //주소
    private String roadAddress;           //도로명
    private String homePageLink;        //홈페이지 주소
    private String imageLink;              //음식, 가제 이미지 주소
    private boolean isVisit;                //방문여부
    private int visitCount;                 //방문수
    private LocalDateTime lastVisitDate;    //마지막 방문날짜


}
