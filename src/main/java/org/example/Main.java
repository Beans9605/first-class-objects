package org.example;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.example.firstclass.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public enum UserRole {
        ADMIN,
        MEMBER,
        GUEST;

        public String getId() {
            return this.name();
        }
    }

    static class Person {
        private final String name;
        private String sex;
        private UserRole userRole;

        public Person(String name, String sex) {
            this.name = name;
            this.sex = sex;
        }
        public Person(String name, UserRole userRole) {
            this.name = name;
            this.userRole = userRole;
        }

        public String getName() {
            return name;
        }

        public String getSex() {
            return this.sex;
        }

        public UserRole getUserRole() {
            return this.userRole;
        }
    }

    static class Cat {
        String name;

        public Cat(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }
    static class Dog {
        String name;

        public Dog(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static void main(String[] args) {
        Cat cat = new Cat("name");
        Cat cat1 = new Cat("name");
        Cat cat2 = new Cat("name");

        Dog dog = new Dog("name");
        Dog dog1 = new Dog("name");
        Dog dog2 = new Dog("name");

        List< Cat > cats = new ArrayList<>(List.of(cat, cat1, cat2));
        List< Dog > dogs = new ArrayList<>(List.of(dog, dog1, dog2));

        String distinctName = Util.getSingleDataByCollections(
                Pair.of(cats, (Function< Cat, String >) Cat::getName),
                Pair.of(dogs, (Function< Dog, String >) Dog::getName)
        );

        //TIP name
        System.out.println(distinctName);

        Person james = new Person("james", "male");
        Person john = new Person("john", "male");
        Person anna = new Person("anna", "female");

        List < Person > people = new ArrayList<>(List.of(james, john, anna));

        Pair < List < Person >, List < Person > > pair =
                Util.getPairWithCollectionsByFlag(
                        people,
                        Person::getSex,
                        "male"
                );

        //TIP pair.getLeft() -> james, john<br/>
        // pair.getRight() -> anna
        System.out.println(pair);

        james = new Person("james", UserRole.ADMIN);
        john = new Person("john", UserRole.GUEST);
        anna = new Person("anna", UserRole.GUEST);
        Person ben = new Person("ben", UserRole.MEMBER);
        Person kim = new Person("kim", UserRole.MEMBER);
        Person hooker = new Person("hooker", UserRole.MEMBER);

        List < Person > users = new ArrayList<>(List.of(james, john, anna, ben, kim, hooker));

        Triple< List < Person >, List < Person >, List < Person > > triple =
                Util.getTripleWithCollectionsByFlag(
                        users,
                        Person::getUserRole,
                        UserRole.ADMIN,
                        UserRole.MEMBER,
                        UserRole.GUEST
                );

        //TIP triple.getLeft() -> james => UserRole.ADMIN<br/>
        //triple.getMiddle() -> ben, kim, hooker => UserRole.MEMBER<br/>
        //triple.getRight() -> john, anna => UserRole.GUEST
        System.out.println(triple);

        List<Pair<String, UserRole>> listPair = Util.getListPairByFunctions(
                users,
                Person::getName,
                Person::getUserRole
        );

        //TIP listPair.getLeft() -> james, john, anna, ben, kim, hooker<br/>
        // listPair.getRight() -> ADMIN, GUEST, GUEST, MEMBER, MEMBER, MEMBER
        System.out.println(listPair);
    }
}