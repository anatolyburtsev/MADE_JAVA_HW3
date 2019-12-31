import model.Address;
import model.Person;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Address address = Address.builder()
                .zipcode("V6V6V6")
                .street("Barclay st")
                .buildingNumber(178)
                .build();
        Person p = Person.builder()
                .age(17).name("Ivan")
                .address(address)
                .phoneNumbers(List.of("123", "567", "890")).build();
        Serializer sj = ContextSerializer.newJsonSerializer();
        System.out.println(sj.serialize(p));

        Serializer sx = ContextSerializer.newXmlSerializer();
        System.out.println(sx.serialize(p));
    }
}
