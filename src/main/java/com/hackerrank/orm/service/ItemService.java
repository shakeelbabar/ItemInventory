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
        if(this.deleteItem(item.getItemId())){
            return this.itemRepository.save(item);
        }
        else return null;
    }

    public Boolean deleteItem(int itemId){
        Item item = this.itemRepository.findById(itemId).get();
        itemRepository.delete(item);
        return !this.checkIfExists(item);
    }

    public Boolean deleteAll(){
        this.itemRepository.deleteAll();
        return this.itemRepository.findAll().size()==0;
    }

    public List<Item> getAll(){
        return this.itemRepository.findAll();
    }
}
