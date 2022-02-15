package hello.boot.jpa.domain.item;

import hello.boot.jpa.domain.Category;
import hello.boot.jpa.exception.NoEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="dtype")
@Getter
@Setter
@NoArgsConstructor
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "items")
    private List  <Category> categories =new ArrayList<>();

    private int price;
    private int stockQuantity;


    // 비지니스 로직
    // stock 증가
    public void addStock(int quantity) {
        this.stockQuantity +=quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NoEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;

    }

}
