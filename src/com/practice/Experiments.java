package com.practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Experiments {
    static List<Person> persons = new ArrayList<>();

    public static void main(String[] args) {
        Person p1 = new Person("Ashish", "Awasthi", 40);
        Person p2 = new Person("Garima", "Dwivedi", 37);
        Person p3 = new Person("Mahika", "Awasthi", 10);
        Person p4 = new Person("Advik", "Awasthi", 4);
        Person p5 = new Person("Suman", "Dwivedi", 65);
        persons.add(p1);
        persons.add(p2);
        persons.add(p3);
        persons.add(p4);
        persons.add(p5);


        //comparators();
        //iterateMap();
        //treeMap();
        priorityQueue();
        //arrayStream();
        //mapOperations();
        //stringNumSort();
    }

    public static void stringNumSort() {
        //String with Long numbers
        String[] counts = {
                "POINT,333858038",
                "NOT,4522732626",
                "INTO,1144226142",
                "ON,4594521081",
        };
        List<String[]> result = Arrays.stream(counts).map(count -> count.split(","))
                .filter(wordArr -> (wordArr[0].length() <= 5 && wordArr[0].length() >= 2))
                //IMP: Long String comparison, Sort in decreasing order
                .sorted(Comparator.comparingLong(wordArr -> -Long.parseLong(wordArr[1])))
                .limit(3)
                .collect(Collectors.toList());
        result.forEach(wordArr -> System.out.println(Arrays.toString(wordArr)));
    }

        public static void mapOperations () {
            Map<String, String> map = new HashMap<>();
            map.merge("a", "1", String::concat);
            map.merge("a", "2", String::concat);
            map.merge("a", "3", (s1, s2) -> s1 + s2);

            map.forEach((key, value) -> System.out.println(key + " => " + value));
        }

        public static void arrayStream () {
            int[] arr = {6, 2, 9, 5, 4, 1, 7, 8, 3, 10};

            //Filter and Print Even numbers
            Arrays.stream(arr).filter(num -> num % 2 == 0).forEach(System.out::println);

            //Reduce to Sum
            int res = Arrays.stream(arr).sorted().reduce(0, Integer::sum);
            System.out.println(res);

            //Sort in reverse order
            IntStream.rangeClosed(1, 10).boxed().sorted(Comparator.reverseOrder()).forEach(System.out::println);
        }

        public static void priorityQueue () {
            Comparator<Person> comparator1 = (p1, p2) -> p2.age - p1.age;
            Comparator<Person> comparator2 = (p1, p2) -> p2.firstName.compareToIgnoreCase(p1.firstName);
            Comparator<Person> comparator3 = Comparator.comparing(p -> p.firstName.toLowerCase(), Comparator.reverseOrder());
            Queue<Person> queue = new PriorityQueue<>(comparator1);
            queue.add(persons.get(0));
            queue.add(persons.get(1));
            queue.add(persons.get(2));
            queue.add(persons.get(3));
            queue.add(persons.get(4));

            queue.forEach(person -> {
                //System.out.println("==>" + person);
            });

            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }

        }

        public static void treeMap () {
            Comparator<Integer> comparator = (p1, p2) -> p2 - p1;
            TreeMap<Integer, Person> map = new TreeMap<>(comparator);
            map.put(0, persons.get(0));
            map.put(1, persons.get(1));
            map.put(2, persons.get(2));
            map.put(3, persons.get(3));
            map.put(4, persons.get(4));

            map.forEach((k, v) -> System.out.println(k + " => " + v));
        }

        public static void iterateMap () {
            Map<Integer, Person> map = new HashMap<>();
            map.put(0, persons.get(0));
            map.put(1, persons.get(1));
            map.put(2, persons.get(2));
            map.put(3, persons.get(3));
            map.put(4, persons.get(4));

            Set<Map.Entry<Integer, Person>> entrySet = map.entrySet();
            entrySet.forEach(es -> System.out.println(es.getKey() + " => " + es.getValue()));

            for (Map.Entry<Integer, Person> es : entrySet) {
                System.out.println(es.getKey() + " => " + es.getValue());
            }

            Set<Integer> keySet = map.keySet();
            keySet.forEach(ks -> System.out.println(ks + " => " + map.get(ks)));

            Collection<Person> persons = map.values();
            persons.forEach(System.out::println);
        }

        public static void comparators () {
            Comparator<Person> comparator1 = Comparator.comparingInt(p -> -p.age);
            Comparator<Person> comparator2 = (p1, p2) -> p2.age - p1.age;
            Comparator<Person> comparator3 = (p1, p2) -> {
                if (p1.age == p2.age) {
                    if (p1.firstName.equalsIgnoreCase(p2.firstName)) {
                        return p2.lastName.compareToIgnoreCase(p1.lastName);
                    } else {
                        return p2.firstName.compareToIgnoreCase(p1.firstName);
                    }
                } else {
                    return p2.age - p1.age;
                }
            };
            Comparator<Person> comparator4 = Comparator.comparingInt(Person::getAge)
                    .thenComparing(Person::getFirstName)
                    .thenComparing(Person::getLastName)
                    .reversed();
            persons.sort(comparator4);
            print();

        }

        public static void print () {
            persons.forEach(System.out::println);
        }

    }

    class Person {
        String firstName;
        String lastName;
        int age;

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public int getAge() {
            return age;
        }
    }
