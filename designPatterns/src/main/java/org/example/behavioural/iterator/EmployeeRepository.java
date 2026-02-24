package org.example.behavioural.iterator;

public class EmployeeRepository implements Container <String>{

    private String[] employees = {"kranthi","Ravi","Anil"};

    @Override
    public Iterator<String> getIterator(){
        return new EmployeeIterator();


    }

    private class EmployeeIterator implements Iterator<String>{

        int index = 0;

        @Override
        public boolean hasNext(){
            return index < employees.length;
        }

        @Override
        public String next(){
            if(this.hasNext()){
                return employees[index++];
            }
            return null;
        }
    }
}
