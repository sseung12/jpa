package hello.boot.jpa.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Member {

    @Id
    private Long Id;

    @Column(name="name")
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private int temp;

    public Member() {
    }
}
