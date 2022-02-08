package hello.boot.jpa.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    private Long Id;

    @Column(name="name" ,nullable = false,length = 10)
    private String username;

    private Integer age;





}
