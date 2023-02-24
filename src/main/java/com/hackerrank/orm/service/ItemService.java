package com.hackerrank.orm.service;

import com.hackerrank.orm.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hackerrank.orm.model.Item;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ItemService {
    @Autowired
    public ItemRepository itemRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    // create
    public Item create(Item item){
      if(!this.checkIfExists(item))
        return this.itemRepository.save(item);
      else return null;
    }

    // check
    public Boolean checkIfExists(Item item){
      return this.itemRepository.existsById(item.getItemId());
    }

    public Optional<Item> getById(int id){
        return this.itemRepository.findById(id);
    }

    public Item updateItem(Item item){
        Optional<Item> itm = this.getById(item.getItemId());
        if(itm!=null){
            logger.info("Here with "+itm.get().getItemId());
            return this.itemRepository.save(itm.get());
        }
        else return null;
    }

    public Boolean deleteItem(int itemId){
        this.itemRepository.findById(itemId);
        itemRepository.delete(this.itemRepository.findById(itemId).get());
        return this.checkIfExists(this.itemRepository.findById(itemId).get());
    }

    public List<Item> getAll(){
        return this.itemRepository.findAll();
    }
}
