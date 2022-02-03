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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Id;

    @Column(name="name" ,nullable = false,length = 10)
    private String username;

    private Integer age;

//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob
//    private String description;
//
//    @Transient
//    private int temp;


}
