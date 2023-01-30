package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * name:jinhong
 * date:2023-1-30
 * --------
 * arr_err{
 * 1.创建JDBCurl时发现链接超时（把IP改成localhost）
 * 2.主键的数据类型只能是integer
 * 3.增删查改的时候涉及字段调用的时候最好带上表名。例如：表名.字段
 * }
 * --------
 * arr_tips{
 * 2.表创建主键时关键字：primary key(字段)，自增：auto_increment
 * 3.sql语句的时候可以先把字符串放进执行命令，在写sql语句时就会有代码提示
 * }
 */

public class Main {
    public static void main(String[] args) throws SQLException {
        System.out.println("Start...");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123");
        Statement statement = connection.createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------");
        System.out.println("---call 1: 创建表");
        System.out.println("---call 2: 操作表");
        System.out.println("--------");
        switch (Integer.valueOf(scanner.nextLine())) {
            case 1 -> {
                System.out.println("创建表...");
                String sql = "create table if not exists test (id integer auto_increment, name varchar(10),age int,birth date,address varchar(50),primary key(id))";
                statement.executeUpdate(sql);
                System.out.println("创建成功！");
            }
            case 2 -> {
                System.out.println("********");
                System.out.println("---call 1:增");
                System.out.println("---call 2:删");
                System.out.println("---call 3:查");
                System.out.println("---call 4:改");
                System.out.println("---call 0:退出");
                System.out.println("********");
                switch (Integer.valueOf(scanner.nextLine())) {
                    case 1 -> {
                        String sql = "insert into test(test.name,test.age,test.birth,test.address) values ('黄锦鸿',19,20031209,'广东')";
                        statement.executeUpdate(sql);
                        main(new String[0]);
                    }
                    case 2 -> {
                        String sql = "delete from test";
                        statement.executeUpdate(sql);
                        main(new String[0]);
                    }
                    case 3 -> {
                        String sql = "select * from test";
                        ResultSet resultSet = statement.executeQuery(sql);
                        List<String[]> list = new ArrayList<>();
                        while (resultSet.next()) {
                            list.add(new String[]{resultSet.getString("name"), "" + resultSet.getInt("age"), "" + resultSet.getDate("birth"), resultSet.getString("address")});
                        }
                        list.forEach(s -> {
                            for (int i = 0; i < 4; i++) {
                                System.out.print(s[i]);
                                System.out.print("，");
                            }
                            System.out.print("\n");
                        });
                        System.out.print("\n");
                        main(new String[0]);
                    }
                    case 4 -> {
                        String sql = "update test set = 19 where name='黄锦鸿'";
                        statement.executeUpdate(sql);
                        main(new String[0]);
                    }
                    case 0 -> System.exit(0);
                }
            }
        }
        System.out.println("End...");
    }
}