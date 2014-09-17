package com.database.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataSample {

	public static void main(String[] args) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "insert into company "
				+ "(id, name, age, address, salary) "
				+ "values(2, 'Allen', 25, 'Texas', 15000.0);";
			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Records created successfully");
	}

}
