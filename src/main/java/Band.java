import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Band {
  private int id;
  private String name;
  private String genre;


  public Band (String name, String genre) {
    this.name = name;
    this.genre = genre;
  }

  public Band (String name) {
    this.name = name;
    this.genre = "";
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
      return this.getname().equals(newBand.getname());
    }
  }

  public void save() {
    String sql = "INSERT INTO bands (name, genre) VALUES (:name, :genre)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", name)
        .addParameter("genre", genre)
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

  public void update(String newName, String newGenre) {
    String sql ="UPDATE bands SET name = :newName, genre = :newGenre WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("newGenre", newName)
      .addParameter("newGenre", newGenre)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sqlJoin ="DELETE FROM concerts WHERE Band_id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .addParameter("id", id)
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

  public void addConcert (Venue venue, date date) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO concerts (venue_id, band_id, date) VALUES (:band_id, :venue_id, :date)";
      con.createQuery(sql)
      .addParameter("band_id", this.getId())
      .addParameter("venue_id", venue_id.getId())
      .addParameter("date", date)
      .executeUpdate();
    }
  }

  public List<Map<String, Object>> getConcerts() {
    try(Connection con = DB.sql2o.open()) {
      String joinSql = "SELECT venues.location, venues.name, concerts.name, concerts.date FROM concerts " +
      "JOIN venues ON (concerts.venue_id = venues.id) " +
      "WHERE bands.id = :id";
      List concerts = con.createQuery(joinSql)
      .addParameter("id", this.id)
      .addParameter("venues.location", venues.location)
      .addParameter("venues.name", venues.name)
      .addParameter("concerts.name", concerts.name)
      .addParameter("concerts.date", concerts.date)
      .executeAndFetchTable().asList();
    }
  }
}
