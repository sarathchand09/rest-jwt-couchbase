package com.example.nosqlcouchpractice.repository;

import com.example.nosqlcouchpractice.entity.Item;
import com.example.nosqlcouchpractice.entity.Unit;
import com.example.nosqlcouchpractice.entity.User;
import com.example.nosqlcouchpractice.security.JwtTokenProvider;
import com.example.nosqlcouchpractice.service.LoginService;
import java.util.Collections;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ItemRepositoryTest {

  @Autowired private ItemRepository itemRepository;
  @Autowired private UnitRepository unitRepository;
  @Autowired private UserRepository userRepository;

  @Autowired private JwtTokenProvider jwtTokenProvider;
  @Autowired private LoginService loginService;

  //    @BeforeEach
  private void before() {
    Unit unit = new Unit();
    unit.setName("dollor");
    unit.setSymbol("$");
    unit.setUnitId(3l);
    unitRepository.save(unit);
    Item item = new Item();
    item.setCategoryId(4l);
    item.setDescription("wireless headpohones  good one");
    item.setItemId(1l);
    item.setName("headphones");
    item.setUnitId(3l);
    item.setPrice(129l);
    //    itemRepository.save(item);
  }

  //  @BeforeEach
   void before_user() {
    User user = new User();
    user.setUserName("sarath");
    user.setPassword("1234");
    userRepository.save(user);
  }

  @Test
  @Disabled
  public void findAllItemsTest() {
    System.out.println("---------------------------------------------------------------------");
    //      unitRepository.getCouchbaseOperations().findBySpatialView(SpatialViewQuery.from())
    /* System.out.println(unitRepository
    .getCouchbaseOperations()
    .findByView(
        ViewQuery.from(getViewForUnit(),"all"),Unit.class));*/
    System.out.println(itemRepository.findAll());
  }

  @Test
  @Disabled
  public void findAllUnitsTest() {
    System.out.println("------------------------");
    unitRepository.findAll().forEach(unit -> System.out.println(unit));
    //    System.out.println(unitRepository.findById(3l).get());
    System.out.println("------------------------");
    //    Assertions.assertTrue(unitRepository.findById(3l).isPresent());
  }

  @Test
  @Disabled
  public void createJWT() {
    String jwtTokenProviderToken = jwtTokenProvider.createToken("sarath", Collections.EMPTY_LIST);
    System.out.println(jwtTokenProviderToken);
  }

  @Test
  @Disabled
  public void insertMillin() {
    for (int i = 0; i < 1000000; i++) {
      loginService.createUser("test" + i, "test-pass");
    }
  }

}
