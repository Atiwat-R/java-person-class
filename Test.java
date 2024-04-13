import java.util.*;
import java.util.stream.Collectors;

class Person {

    private int age;
    private String firstname;
    private String lastname;
    private String gender;

    // Constructor
    public Person(int age, String firstname, String lastname, String gender) {
        this.age = age;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
    }

    // Getters
    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public int getAge() {
        return this.age;
    }
    public String getGender() {
        return this.gender;
    }

    // For prining
    @Override
    public String toString() {
        return firstname + " " + lastname + ", " + age + ", " + gender;
    }
}


public class Test {

    // Sort alphabetically by Firstname. If the Firstnames are the same, sort by Lastname
    public static void sortByFirstAndLastname(List<Person> lst) {

        // Comparator to compare names
        Comparator<Person> comparator = new Comparator<Person>() {
            public int compare(Person p1, Person p2) {
                int compareFirstname = p1.getFirstname().compareTo(p2.getFirstname());
                if (compareFirstname == 0) {
                    return p1.getLastname().compareTo(p2.getLastname());
                }
                return compareFirstname;
            }
        };

        // Sort the List using comparator
        lst.sort(comparator);
    }

    // Return a new HashMap that groups Person by Gender
    public static Map<String, List<Person>> groupByGender(List<Person> lst) {
        return lst
                .stream()
                .collect(Collectors.groupingBy(Person::getGender));
    }

    // Return a new list with only Person older than 30
    public static List<Person> findThirtyOrOlder(List<Person> lst) {
        return lst
                .stream()
                .filter(person -> person.getAge() >= 30)
                .collect(Collectors.toList());
    }




    public static void main(String[] args) {

        // Testing
        List<Person> lst = new ArrayList<>();
        lst.add(new Person(30, "John", "Doe", "Male"));
        lst.add(new Person(18, "Sarah", "Clegane", "Female"));
        lst.add(new Person(28, "Alice", "Jo", "Female"));
        lst.add(new Person(35, "Bob", "Johnson", "Male"));
        lst.add(new Person(25, "Jane", "Doe", "Female"));
        lst.add(new Person(34, "Arthur", "Pendragon", "Male"));
        lst.add(new Person(75, "Arthur", "Dayne", "Male"));


        System.out.println("-- Sort by First and Lastname");
        sortByFirstAndLastname(lst);
        for (int i=0 ; i<lst.size() ; i++) {
            System.out.println(lst.get(i).toString());
        }

        System.out.println("-- Filter Thirty or older");
        List<Person> lst2 = findThirtyOrOlder(lst);
        for (int i=0 ; i<lst2.size() ; i++) {
            System.out.println(lst2.get(i).toString());
        }

        System.out.println("-- Group by Gender");
        Map<String, List<Person>> lst3 = groupByGender(lst);
        for (Map.Entry<String, List<Person>> entry : lst3.entrySet()) {
            System.out.println(entry.getKey() + ": ");
            for (Person person : entry.getValue()) {
                System.out.println(person.toString());
            }
        }
    }
    
}
