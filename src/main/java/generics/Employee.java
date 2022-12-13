package generics;

public class Employee extends Person{
    public boolean equals(Employee employee) {
        System.out.println("I am here");
        return super.equals(employee);
    }

    public static void main(String[] args) {
        var employee = new Employee();
        System.out.println(employee.equals((Object)new Employee()));
    }
}
