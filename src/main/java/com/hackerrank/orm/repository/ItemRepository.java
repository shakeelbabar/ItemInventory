package com.hackerrank.orm.repository;

import com.hackerrank.orm.model.Item;
import com.hackerrank.orm.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    List<Item> findByItemStatusAndItemEnteredByUser(ItemStatus itemStatus, String itemEnteredByUser);
}
