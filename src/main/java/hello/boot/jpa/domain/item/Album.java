package hello.boot.jpa.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("A")
@Getter
@Setter
public class Album  extends Item {

    private String artist;
    private String etc;
}
