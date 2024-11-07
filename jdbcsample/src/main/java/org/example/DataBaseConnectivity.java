package org.example;

import java.sql.SQLException;

public class DataBaseConnectivity {
    public static void main(String[] args) throws SQLException {
        JDBCExample jdbcExample = new JDBCExample();
       System.out.println("Connection Successful"+jdbcExample.getConnection());
        jdbcExample.createTable();
        jdbcExample.createEmployee(new Employee(1,"Sample1",25));
        jdbcExample.createEmployee(new Employee(2,"Sample2",30));
        jdbcExample.createEmployee(new Employee(3,"Sample3",18));
        jdbcExample.createEmployee(new Employee(4,"Sample4",18));

        jdbcExample.readEmployee();
    }
}