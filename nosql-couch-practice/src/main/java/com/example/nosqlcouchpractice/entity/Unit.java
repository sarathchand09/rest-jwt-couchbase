package com.example.nosqlcouchpractice.entity;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.data.couchbase.core.mapping.id.IdAttribute;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Unit {

  @Id
  @GeneratedValue(strategy = GenerationStrategy.USE_ATTRIBUTES)
  private Long id;

  @Field
  private String name;

  @Field("unit_id")
  @IdAttribute
  private Long unitId;

  @Field
  private String symbol;
}
