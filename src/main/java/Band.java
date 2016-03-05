import java.util.*;
import org.sql2o.*;

public class Band {

  private int id;
  private String name;
  private String genre;


  public Band (String name) {
    this.name = name;
    this.genre = "";
  }

  public Band (String name, String genre) {
    this.name = name;
    this.genre = genre;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getGenre() {
    return genre;
  }

  public List getConcertList(String sortBy) {


    return new ArrayList();
  }

  public void update(String name, String genre) {
    String sql ="UPDATE bands SET name = :name, genre = :genre WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("genre", genre)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public static  List<Band> all() {
    String sql = "SELECT * FROM bands ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand) {
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand = (Band) otherBand;
      return this.getName().equals(newBand.getName());
    }
  }

  public void save() {
    String sql = "INSERT INTO bands (name, genre) VALUES (:name, :genre)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("genre", this.genre)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    String sql = "SELECT * FROM bands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Band Band = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Band.class);
      return Band;
    }
  }

  public void delete() {
    String sqlJoin ="DELETE FROM concerts WHERE Band_id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .addParameter("id", this.id)
        .executeUpdate();
    }
    String sql ="DELETE FROM Bands WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static void deleteAll() {
    String sqlJoin ="DELETE FROM Concerts";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .executeUpdate();
    }
    String sql ="DELETE FROM Bands ";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .executeUpdate();
    }
  }

  public void addConcert (Venue venue, Date date) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO concerts (venue_id, band_id, date) VALUES (:band_id, :venue_id, :date)";
      con.createQuery(sql, true)
      .addParameter("band_id", this.getId())
      .addParameter("venue_id", venue.getId())
      .addParameter("date", date)
      .executeUpdate()
      .getKey();
    }
  }


  public List<Map<String, Object>> getConcerts() {
    try(Connection con = DB.sql2o.open()) {
      String joinSql = "SELECT venues.location, venues.name, concerts.name, concerts.date FROM concerts " +
      "JOIN venues ON (concerts.venue_id = venues.id) " +
      "WHERE bands.id = :id";
      List concerts = con.createQuery(joinSql)
      .addParameter("id", this.id)
      .executeAndFetchTable().asList();
      return concerts;
    }
  }
}
