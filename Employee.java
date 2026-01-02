import java.util.Scanner;
import java.util.UUID;

public class Employee extends Showroom implements utility{
  String emp_id;
  String emp_name;
  int emp_age;
  String emp_dept; //department

    @Override
    public void get_details()
    {
        System.out.println("ID :- "+emp_id);
        System.out.println("Name :- "+emp_name);
        System.out.println("Age :- "+emp_age);
        System.out.println("Department :- "+emp_dept);
        System.out.println("Showroom name :- "+showroom_name);
    }

    @Override
    public void set_details(){
        Scanner sc = new Scanner(System.in);
        UUID uuid = UUID.randomUUID();
        emp_id = String.valueOf(uuid);
        System.out.println("=====================***Enter Employee Details ***=====================");
        System.out.println();
        System.out.println("Employee name :- ");
        emp_name = sc.nextLine();
        System.out.println("Employee age :- ");
        emp_age = sc.nextInt();
        sc.nextLine();
        System.out.println("Employee department :- ");
        emp_dept = sc.nextLine();
        System.out.println("Showroom name :- ");
        showroom_name = sc.nextLine();
    }


}
