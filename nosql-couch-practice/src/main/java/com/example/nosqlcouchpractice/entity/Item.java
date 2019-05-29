package com.example.nosqlcouchpractice.entity;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;
import org.springframework.data.couchbase.core.query.N1qlJoin;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Item {

  @Id private String id = UUID.randomUUID().toString();

  @Field("item_id")
  private Long itemId;

  @Field private String name;

  @Field("category_id")
  private Long categoryId;

  @Field private String description;

  @Field private Long price;

  @Field("unit_id")
  private Long unitId;

//  @N1qlJoin(on = "lks.unitId=rks.unitId")
  private Unit unit;
}
