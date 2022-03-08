/*
1. Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
2. Конструктор класса должен заполнять эти поля при создании объекта.
3. Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
4. Создать массив из 5 сотрудников.
5. С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
 */
 
public class FiveLessonTasks {
    public static void main(String[] args) {

        Employee[] employeesArray = new Employee[5];
            employeesArray[0] = new Employee("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);

            employeesArray[1] = new Employee("Petrov Alexandr ", "Actor", "acorpetrov@google.com", "892312312", 500000, 33);

            employeesArray[2] = new Employee("Pupkin Vasya ", "Student", "vpupkin@mail.ru", "892197986", 3000, 19);

            employeesArray[3] = new Employee("Smirnova Nina", "Teacher", "ninasmirnova@rambler.ru", "897945666", 30000, 70);

            employeesArray[4] = new Employee("Kuznetsov Nikita", "Guard", "kuznik@yandex.com", "895236985", 50000, 49);

        for (int i = 0; i < employeesArray.length; i++) {

            if (employeesArray[i].getAge() > 40) {
                employeesArray[i].informationOutput();
            }

        }

    }
}

class Employee {
    private String fullName;
    private String jobRole;
    private String mail;
    private String phoneNumber;
    private long salary;
    private int age;

    public Employee(String fullName, String jobRole, String mail, String phoneNumber, long salary, int age) {
        this.fullName = fullName;
        this.jobRole = jobRole;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }
    
    public int getAge() {
        return age;
    }
    
    public void informationOutput () {
        System.out.print("fullName: " + this.fullName + "\n");
        System.out.print("jobRole: " + this.jobRole + "\n");
        System.out.print("mail: " + this.mail + "\n");
        System.out.print("phoneNumber: " + this.phoneNumber + "\n");
        System.out.print("salary: " + this.salary + "\n");
        System.out.print("age: " + this.age + "\n");
        System.out.println();
    }

}

