package com.example.nosqlcouchpractice.repository;

import com.example.nosqlcouchpractice.entity.Item;
import java.util.List;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@ViewIndexed(designDoc = "item")
@N1qlPrimaryIndexed
public interface ItemRepository extends CrudRepository<Item, Long> {

    @Query("#{#n1ql.selectEntity}")
    List<Item> findAllItems();

}
