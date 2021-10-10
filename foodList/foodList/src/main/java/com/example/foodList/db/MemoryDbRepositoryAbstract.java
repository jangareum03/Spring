package com.example.foodList.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class MemoryDbRepositoryAbstract<T extends  MemoryDbEntity> implements MemoryDbRepositoryIfs<T>{

    private final List<T> db = new ArrayList<>();
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index).findFirst(); //리스트에서 매개변수와 MemoryDbEntity에 있는 index값이 같은 값중에 가장 앞에 요소를 리턴
    }

    @Override
    public T save(T entity) {

        var e = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(e.isEmpty()){
            // db에 데이터가 존재하지 않는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);

            return entity;
        }else{
            // db에 데이터 존재하는 경우
            var preIndex = e.get().getIndex();
            entity.setIndex(preIndex);

            deleteByID(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteByID(int index) {
        var e = db.stream().filter(it -> it.getIndex() == index).findFirst();

        if(e.isPresent()){
            db.remove(e.get());
        }
    }

    @Override
    public List<T> findAll() {
        return db;
    }
}
