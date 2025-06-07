class Employee {
    private int pwd;
    protected int salary;
    public int empid;

    public void setPwd(int p) {
        pwd = p;
    }

    public int getPwd() {
        return pwd;
    }

    public void display() {
        System.out.println("Password: " + pwd);
        System.out.println("Salary: " + salary);
        System.out.println("EmpID: " + empid);
    }
}

    class Hr extends Employee {
        public void setValues() {
            setPwd(1254);
            salary = 50000;
            empid = 10001;
        }
    }

    public class Task037 {
        public static void main(String[] args) {
            Hr obj = new Hr();
            obj.setValues();
            obj.display();
        }
    }
