package hello.boot.jpa.service;

import hello.boot.jpa.domain.Item;
import hello.boot.jpa.repository.ItemRepository;
import hello.boot.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {


    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findOne(Long id) {
        return  itemRepository.findOne(id);
    }

    public List<Item> findAll() {
        return  itemRepository.findAll();
    }

    @Transactional
    public void updateItem(Long id, String name, int price) {
        Item item = itemRepository.findOne(id);
        item.setName(name);
        item.setPrice(price);

    }

}
