package com.example.foodList.wishlist.service;

import com.example.foodList.naver.NaverClient;
import com.example.foodList.naver.dto.SearchImageReq;
import com.example.foodList.naver.dto.SearchLocalReq;
import com.example.foodList.wishlist.dto.WishListDto;
import com.example.foodList.wishlist.entity.WishListEntity;
import com.example.foodList.wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;
    private final WishListRepository wishListRepository;

    public WishListDto search(String query){
        //지역검색
        var searchLocalReq = new SearchLocalReq();
        searchLocalReq.setQuery(query);

        var searchLocalRes = naverClient.searchLocal(searchLocalReq);

        if(searchLocalRes.getTotal() > 0){
            //검색 결과가 존재
            var localItem = searchLocalRes.getItems().stream().findFirst().get();

            var imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            var searchImageReq = new SearchImageReq();
            searchImageReq.setQuery(imageQuery);

            //이미지검색
            var searchImageRes = naverClient.imageSearch(searchImageReq);

            if(searchImageRes.getTotal() > 0){
                var imageItem = searchImageRes.getItems().stream().findFirst().get();

                var result = new WishListDto();
                result.setTitle(localItem.getTitle());
                result.setCategory(localItem.getCategory());
                result.setAddress(localItem.getAddress());
                result.setRoadAddress(localItem.getRoadAddress());
                result.setImageLink(imageItem.getThumbnail());
                result.setHomePageLink(imageItem.getLink());

                return result;
            }
        }

        return new WishListDto();
    }

    public WishListDto add(WishListDto wishListDto) {
        var entity = dtoToEntity(wishListDto);

        var saveEntity = wishListRepository.save(entity);

        return entityToDto(saveEntity);
    }

    private WishListEntity dtoToEntity(WishListDto wishListDto){
        var entity = new WishListEntity();
        entity.setTitle(wishListDto.getTitle());
        entity.setCategory(wishListDto.getTitle());
        entity.setIndex(wishListDto.getIndex());
        entity.setAddress(wishListDto.getAddress());
        entity.setRoadAddress(wishListDto.getRoadAddress());
        entity.setHomePageLink(wishListDto.getHomePageLink());
        entity.setImageLink(wishListDto.getImageLink());
        entity.setVisit(wishListDto.isVisit());
        entity.setVisitCount(wishListDto.getVisitCount());
        entity.setLastVisitDate(wishListDto.getLastVisitDate());

        return entity;
    }

    private WishListDto entityToDto(WishListEntity wishListEntity){
        var dto = new WishListDto();
        dto.setTitle(wishListEntity.getTitle());
        dto.setCategory(wishListEntity.getTitle());
        dto.setIndex(wishListEntity.getIndex());
        dto.setAddress(wishListEntity.getAddress());
        dto.setRoadAddress(wishListEntity.getRoadAddress());
        dto.setHomePageLink(wishListEntity.getHomePageLink());
        dto.setImageLink(wishListEntity.getImageLink());
        dto.setVisit(wishListEntity.isVisit());
        dto.setVisitCount(wishListEntity.getVisitCount());
        dto.setLastVisitDate(wishListEntity.getLastVisitDate());

        return dto;
    }

    public List<WishListDto> findAll() {

        return wishListRepository.findAll().stream().map(it -> entityToDto(it)).collect(Collectors.toList());
    }

    public void delete(int index) {
        wishListRepository.deleteByID(index);
    }

    public void addVisit(int index){
        var wishItem = wishListRepository.findById(index);

        if(wishItem.isPresent()){
            var item = wishItem.get();

            item.setVisit(true);
            item.setVisitCount(item.getVisitCount()+1);
        }
    }
}
