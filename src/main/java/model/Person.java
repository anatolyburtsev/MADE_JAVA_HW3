package model;

import lombok.Builder;

import java.util.Collection;

@Builder
public class Person {
    int age;
    String name;
    Address address;
    Collection<String> phoneNumbers;
}
