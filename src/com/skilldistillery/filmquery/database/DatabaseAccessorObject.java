package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";
	private Connection conn;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film newFilm = null;
		try {
			createConnection();
			PreparedStatement ps = createPreparedStatement("SELECT * from film WHERE id = ?");
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				newFilm = new Film(rs.getInt("id"), rs.getString("title"), rs.getString("description"),
						rs.getInt("release_year"), rs.getInt("language_id"), rs.getString("rental_duration"),
						rs.getDouble("rental_rate"), rs.getString("length"), rs.getDouble("replacement_cost"),
						rs.getString("rating"), rs.getString("special_features"));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newFilm;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor newActor = null;
		try {
			createConnection();
			PreparedStatement ps = createPreparedStatement("SELECT * from actor WHERE id = ?");
			ps.setInt(1, actorId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				newActor = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return newActor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		return null;
	}
	
	private void getActorsInFilm() {
		
	}

	private void createConnection() throws SQLException {
		conn = DriverManager.getConnection(URL, user, pass);
	}

	private PreparedStatement createPreparedStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

}
