import java.util.*;
import org.sql2o.*;
import java.sql.Date;

public class Concert {

  private int id;
  private int band_id;
  private int venue_id;
  private Date date;


  public Concert (int band_id, int venue_id, int year, int month, int dayOfMonth) {
    this.band_id = band_id;
    this.venue_id = venue_id;
    this.date = Date.valueOf(Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth));
  }

  public Concert (String venueName, int band_id, int year, int month, int dayOfMonth) {
    this.band_id = band_id;
    this.venue_id = venue_id;
    this.date = Date.valueOf(Integer.toString(year) + Integer.toString(month) + Integer.toString(dayOfMonth));
  }

  // public Concert (String band, )

  public String toString() {
    return Venue.find(venue_id).toString() + " - " + date.toString();
  }

  public int getId() {
    return id;
  }

  public String getBandName() {
    return Band.find(band_id).getName();
  }

  public String getVenueName() {
    return Venue.find(venue_id).getName();
  }

//   public static  List<Concert> all() {
//     String sql = "SELECT * FROM concerts";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(Concert.class);
//     }
//   }
//
//   @Override
//   public boolean equals(Object otherConcert) {
//     if (!(otherConcert instanceof Concert)) {
//       return false;
//     } else {
//       Concert newConcert = (Concert) otherConcert;
//       return this.getTitle().equals(newConcert.getTitle()) &&
//       this.getId() == newConcert.getId();
//     }
//   }
//
//   public void save() {
//     String sql = "INSERT INTO concerts (title) VALUES (:title)";
//      try(Connection con = DB.sql2o.open()) {
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("title", title)
//         .executeUpdate()
//         .getKey();
//     }
//   }
//
//   public static Concert find(int id) {
//     String sql = "SELECT id, title FROM concerts WHERE id = :id";
//     try(Connection con = DB.sql2o.open()) {
//       Concert concert = con.createQuery(sql)
//       .addParameter("id", id)
//       .executeAndFetchFirst(Concert.class);
//       return concert;
//     }
//   }
//
//   public void update(String title) {
//     String sql ="UPDATE concerts SET title = :title WHERE id = :id";
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery(sql)
//       .addParameter("title", title)
//       .addParameter("id", id)
//       .executeUpdate();
//     }
//   }
//
//   public void delete() {
//     String sqlJoin ="DELETE FROM bands_concerts WHERE concert_id = :id";
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery(sqlJoin)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//     String sql ="DELETE FROM concerts WHERE id = :id";
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery(sql)
//         .addParameter("id", id)
//         .executeUpdate();
//     }
//   }
//
//   public static void deleteAll() {
//     String sqlJoin ="DELETE FROM bands_concerts";
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery(sqlJoin)
//         .executeUpdate();
//     }
//     String sql ="DELETE FROM concerts ";
//     try(Connection con = DB.sql2o.open()) {
//       con.createQuery(sql)
//         .executeUpdate();
//     }
//   }
//
//   public void addBand (Band band) {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO bands_concerts (band_id, concert_id) VALUES (:band_id, :concert_id)";
//       con.createQuery(sql)
//       .addParameter("concert_id", this.getId())
//       .addParameter("band_id", band.getId())
//       .executeUpdate();
//     }
//   }
//
//   public List<Band> getBands() {
//     try(Connection con = DB.sql2o.open()) {
//
//       String sql = "SELECT bands.* FROM concerts " +
//       "JOIN bands_concerts ON (concerts.id = concert_id) " +
//       "JOIN bands ON (band_id = bands.id) " +
//       "WHERE concerts.id = :id";
//       List<Band> bands = con.createQuery(sql)
//       .addParameter("id", id)
//       .executeAndFetch(Band.class);
//       return bands;
//     }
//   }


} // END Concert CLASS
