import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Venue {

  private int id;
  private String name;
  private String location;


  public Venue(String name, String location) {
    this.name = name;
    this.location = location;
  }

  public int getId() {
    return this.id;
  }

  public String getName() {
    return this.name;
  }

  public String getLocation() {
    return location;
  }

  public String toString() {
    return name + " (" + location + ")";
  }

  public static  List<Venue> all() {
    String sql = "SELECT * FROM venues ORDER BY name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Venue.class);
    }
  }

  public static Venue find(int id) {
    String sql = "SELECT * FROM venues WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Venue.class);
    }
  }

  public static Venue find(String name) {
    String sql = "SELECT * FROM venues WHERE name = :name";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("name", name)
      .executeAndFetchFirst(Venue.class);
    }
  }

  public void save() {
    String sql = "INSERT INTO venues (name, location) VALUES (:name, :location)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .addParameter("location", this.location)
        .executeUpdate()
        .getKey();
    }
  }

  @Override
  public boolean equals(Object otherVenue) {
    if (!(otherVenue instanceof Venue)) {
      return false;
    } else {
      Venue newVenue = (Venue) otherVenue;
      return this.getName().equals(newVenue.getName())
          && this.getLocation().equals(newVenue.getLocation());
    }
  }


} // END Venue CLASS
