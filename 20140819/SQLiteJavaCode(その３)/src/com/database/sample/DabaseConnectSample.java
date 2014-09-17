package com.database.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DabaseConnectSample {

	public static void main(String[] args)  {
		Connection c = null;
		// データベース接続
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Opened database successfully");

		// テーブルの作成
		try {
			Statement stmt = c.createStatement();
			String sql = "create table company " +
				"(id integer primary key not null," +
				"name text not null, " +
				"age ineger not null, " +
				"address text," +
				"salary real)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Table created successfully");
	}

}
