package model;

import lombok.Builder;

import java.util.Collection;
import java.util.List;

@Builder
public class Person {
    private int age;
    private String name;
    private Address address;
    private List<String> phoneNumbers;
}
