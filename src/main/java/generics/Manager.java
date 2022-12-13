package generics;

public class Manager extends Employee{
    private int rank;

    public Manager(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "rank=" + rank +
                '}';
    }
}
