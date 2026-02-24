package org.example.behavioural.iterator;

public class IteratorClient {

    public static void main(String[] args) {
        EmployeeRepository repo = new EmployeeRepository();
        Iterator<String> iterator = repo.getIterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
