package ProjectStudentEnrollmentSystem;

import java.util.Objects;
//Domain Objects
class Course implements Comparable<Course> {
    private final String code;
    private final String name;
    private final int    credits;
    private final int    maxCapacity;
    private int          enrolled;

    public Course(String code, String name, int credits, int maxCapacity) {
        this.code        = code;
        this.name        = name;
        this.credits     = credits;
        this.maxCapacity = maxCapacity;
        this.enrolled    = 0;
    }

    public boolean hasSpace()  {
        return enrolled < maxCapacity;
    }
    public boolean enroll()    { if (!hasSpace()) return false; enrolled++; return true; }
    public boolean unenroll()  { if (enrolled<=0) return false; enrolled--; return true; }
    public int getSpots()      { return maxCapacity - enrolled; }

    public String  getCode()       { return code; }
    public String  getName()       { return name; }
    public int     getCredits()    { return credits; }
    public int     getMaxCapacity(){ return maxCapacity; }
    public int     getEnrolled()   { return enrolled; }

    @Override
    public int compareTo(Course other) {
        return this.code.compareTo(other.code); // natural order by code
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        return code.equals(((Course) o).code);
    }

    @Override public int hashCode() { return Objects.hash(code); }

    @Override
    public String toString() {
        return String.format("%-8s %-25s %d cr | %d/%d enrolled",
                code, name, credits, enrolled, maxCapacity);
    }
}
