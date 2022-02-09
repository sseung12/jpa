package hello.boot.jpa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Embeddable
@Getter
@AllArgsConstructor
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {

    }


}
