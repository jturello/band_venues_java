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

  public String getName() {
    return this.name;
  }

  public int getId() {
    return this.id;
  }

  public String toString() {
    return name + " (" + location + ")";
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



} // END Venue CLASS
