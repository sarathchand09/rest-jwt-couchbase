package com.example.nosqlcouchpractice.repository;

import com.example.nosqlcouchpractice.entity.Unit;
import java.util.List;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "unit")
public interface UnitRepository extends CouchbaseRepository<Unit, Long> {
  @Query("#{#n1ql.selectEntity} ")
  List<Unit> findAllUnits();
}
