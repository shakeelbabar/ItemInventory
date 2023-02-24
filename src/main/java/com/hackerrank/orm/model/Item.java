package com.hackerrank.orm.model;

import com.hackerrank.orm.enums.ItemStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private String itemName;
    private String itemEnteredByUser;
    @CreationTimestamp
    private Timestamp itemEnteredDate;
    private double itemBuyingPrice;
    private double itemSellingPrice;
    @Temporal(TemporalType.DATE)
    private Date itemLastModifiedDate;
    private String itemLastModifiedByUser;
    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus = ItemStatus.AVAILABLE;
}
