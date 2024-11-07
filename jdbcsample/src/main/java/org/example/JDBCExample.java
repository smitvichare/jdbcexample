package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCExample {
    public Connection getConnection()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sample1";
        String user = "root";
        String password = "smit@123";
        return DriverManager.getConnection(url,user,password);
    }

    public void createTable(){
        String sqlQuery = "create table employee(id int primary key,name varchar(50),age int)";
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){
            int rows = statement.executeUpdate(sqlQuery);
            System.out.println("Table created Successfully"+rows);
        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
    public void createEmployee(Employee employee){
        String sqlQuery = "insert into employee(id,name,age) values (?,?,?)";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)){

            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setInt(3,employee.getAge());

            int rows = preparedStatement.executeUpdate();
            System.out.println("Employee added successfully");
            System.out.println("Rows Effected: " +rows);

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }

    public void readEmployee(){
        List<Employee> employeeList = new ArrayList<>();
        String sqlQuery = "select * from employee";

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Employee employee = new Employee(id,name,age);
                employeeList.add(employee);
                System.out.println("ID: " +id+ ", Name: " +name+ ", Age: " +age);
            }

        }catch (SQLException exception){
            exception.printStackTrace();
        }
    }
}