package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;

public class UserDao {
	private Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean userRegister(User u) {
		boolean f=false;
		try {
			String query="insert into user_dtls(full_name,email,password)values(?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(query);
			pstmt.setString(1, u.getFullName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPassword());
			int i=pstmt.executeUpdate();
			if(i==1) {
				f=true;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	public User login(String em,String ps) {
		User u=null;
		try {
			String sql="select * from user_dtls where email=? & password=?";
			PreparedStatement pstmt1=conn.prepareStatement(sql);
			pstmt1.setString(1, em);
			pstmt1.setString(2, ps);
			ResultSet res=pstmt1.executeQuery();
			while(res.next()==true) {
				u=new User();
				u.setId(res.getInt(1));
				u.setFullName(res.getString(2));
				u.setEmail(res.getString(3));
				u.setPassword(res.getString(4));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}
}