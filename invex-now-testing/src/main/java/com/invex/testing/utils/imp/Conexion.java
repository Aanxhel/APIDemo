package com.invex.testing.utils.imp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Conexion {

	Logger logger = LogManager.getLogger(Conexion.class);
	public static Conexion instance;// conectar el singelton
	private Connection cnn;

	public Connection getCnn() {
		return cnn;
	}

	public void setCnn(Connection cnn) {
		this.cnn = cnn;
	}

	private Conexion() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cnn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/datos_persona?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "admin01");// url
		} catch (ClassNotFoundException e) {
			logger.info(e.toString());
		} catch (SQLException e) {
			logger.info(e.toString());

		}

	}

	public synchronized Conexion estatusConexion() {
		if (instance == null) {
			instance = new Conexion();

		}
		return instance;
	}

	public void cerrarConexion() {
		instance = null;

	}

}
