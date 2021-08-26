package com.sanjib.boot.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class CustomSequenceGen implements IdentifierGenerator {
	private static final String COUNT_QUERY = "select count(user_name) as Id from user_registration";

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String prefix = "ICICI";
		int id = 0;
		String generatedValue = null;
		try {
			// create Connection Object
			con = session.connection();
			// create statement Object
			st = con.createStatement();
			rs = st.executeQuery(COUNT_QUERY);
			if (rs.next()) {
				id = rs.getInt(1) + 101;
				generatedValue = prefix + new Integer(id);
			} // if

		} catch (Exception e) {
			e.printStackTrace();
			// throw e;
		} // catch()
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} // finally

		return generatedValue;
	}

}//class