package org.example.Creational.prototype;

public class Employee implements prototype<Employee>{
    private String name;
    private String role;

    public void setName(String name) {
        this.name = name;
    }

    public Employee(String name, String role){
        this.name=name;
        this.role=role;
    }

    @Override
    public Employee clone() {
        return  new Employee(this.name,this.role);
    }

    @Override
    public String toString(){
        return name+ " - "+role;
    }
}
