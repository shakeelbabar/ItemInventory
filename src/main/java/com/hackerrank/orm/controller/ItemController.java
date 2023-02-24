package com.hackerrank.orm.controller;

import com.hackerrank.orm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.hackerrank.orm.model.Item;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/app")
public class ItemController {
    @Autowired
    ItemService itemService;

    //1. insert POST
    @PostMapping("/item")
    public ResponseEntity<Item> create(@RequestBody Item item){
      Item res = this.itemService.create(item);
      if(res!=null)
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
      else return ResponseEntity.badRequest().body(res);
    }

    //2. update PUT
    @PutMapping("/item/{itemId}")
    public ResponseEntity<Item> update(@RequestBody Item item, @PathVariable int itemId){
        if(item.getItemId() == itemId){
            Item res = this.itemService.updateItem(item);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } else return ResponseEntity.badRequest().body(null);
    }



    //3. delete by itemId DELETE
    @DeleteMapping ("/item/{itemId}" )
    public ResponseEntity<Item> delete(@PathVariable int itemId){
        if(this.itemService.deleteItem(itemId))
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.badRequest().body(null);
    }

//    4. delete all DELETE
    @DeleteMapping ("/item" )
    public ResponseEntity<Item> delete(){
        if(this.itemService.deleteAll())
            return ResponseEntity.status(HttpStatus.OK).body(null);
        else return ResponseEntity.badRequest().body(null);
    }


    //5. get by itemId GET
    @GetMapping ("/item/{itemId}" )
    public ResponseEntity<Item> get(@PathVariable int itemId){
        Item item = this.itemService.getById(itemId).get();
        if(item!=null)
            return ResponseEntity.status(HttpStatus.OK).body(item);
        else return ResponseEntity.badRequest().body(null);
    }


    //6. get all GET
    @GetMapping ("/item/all" )
    public ResponseEntity<List<Item>> getAll(){
        List<Item> list = this.itemService.getAll();
        if(list.size()>0)
            return ResponseEntity.status(HttpStatus.OK).body(list);
        else return ResponseEntity.badRequest().body(null);
    }

    //7. filters by fields ?itemStatus={status}&itemEnteredByUser={modifiedBy} GET


    //8. select all with sorting and pagination ?pageSize={pageSize}&page={page}&sortBy={sortBy} GET

}
