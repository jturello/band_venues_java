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



} // END Venue CLASS
