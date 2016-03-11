import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.sql.Date;

public class BandTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    Band firstBand = new Band("The Band");
    Band secondBand = new Band("The Band", "Rock");
    assertTrue(firstBand.getName().equals(secondBand.getName()));
  }

  @Test
  public void save_addsInstanceOfBandToDatabase() {
    Band newBand = new Band("newBand");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void save_assignsIdToObject() {
    Band newBand = new Band("Red Hot Chili Peppers");
    newBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(newBand.getId(), savedBand.getId());
  }

  @Test
  public void find_locatesAllBandInstancesInDatabaseUsingId() {
    Band newBand = new Band("The Dave Matthews Band");
    newBand.save();
    Band savedBand = Band.find(newBand.getId());
    assertTrue(newBand.equals(savedBand));
  }

  @Test
  public void update_updatesBandName_true() {
    Band newBand = new Band("Katy Perry", "Rock");
    newBand.save();
    newBand.update("The Katy Perry Band", "Pop");
    assertEquals(Band.all().get(0).getName(), ("The Katy Perry Band"));
  }

  @Test
  public void update_updatesBandGenre_true() {
    Band newBand = new Band("Katy Perry", "Rock");
    newBand.save();
    newBand.update("The Katy Perry Band", "Pop");
    assertEquals(Band.all().get(0).getGenre(), ("Pop"));
  }

  @Test
  public void delete_deletesSelectedBandFromDatabase_true() {
    Band band = new Band("The Ramons");
    band.save();
    band.delete();
    assertEquals(0, Band.all().size());
  }

  @Test
  public void delete_doesNotDeletesNonSelectedBandFromDatabase_true() {
    Band firstBand = new Band("The Ramons");
    Band secondBand = new Band("Huey Lewis and the News");
    firstBand.save();
    secondBand.save();
    firstBand.delete();
    assertEquals(1, Band.all().size());
    assertEquals("Huey Lewis and the News", Band.all().get(0).getName());
  }

    @Test
    public void deleteAll_deletesAllBandsAndConcerts() {
        Band firstBand = new Band("The Ramons");
        Band secondBand = new Band("Huey Lewis and the News");
        firstBand.save();
        secondBand.save();
        Venue venue = new Venue("The Hollywood Bowl", "Hollywood");
        venue.save();
        Band.deleteAll();
        assertEquals(0, Band.all().size());
    }


  @Test
  public void addConcert_addsConcertToBand() {
    Band band = new Band("The Supremes", "Motown");
    band.save();
    Venue venue = new Venue("The Hollywood Bowl", "Hollywood");
    venue.save();
    Date date1 = Date.valueOf("1965-06-15");

    band.addConcert(venue, date1);

    assertEquals(date1, band.getConcerts().get(0).get("date"));
    assertEquals(venue.getName(), band.getConcerts().get(0).get("name"));
    assertEquals(venue.getLocation(), band.getConcerts().get(0).get("location"));
  }


  @Test
  public void addConcert_returnsResultsInDateOrderDescending_true() {
    Band band = new Band("The Supremes", "Motown");
    band.save();
    Venue venue = new Venue("The Hollywood Bowl", "Hollywood");
    venue.save();
    Date date1 = Date.valueOf("1968-06-15");
    Date date2 = Date.valueOf("1968-01-01");

    band.addConcert(venue, date1);
    band.addConcert(venue, date2);
    assertEquals(date2, band.getConcerts().get(0).get("date"));
  }

  @Test
  public void addConcert_throwsExceptionWhenUniqueConstraintThrown_true() {
    Band band = new Band("The Supremes", "Motown");
    band.save();
    Venue venue = new Venue("The Hollywood Bowl", "Hollywood");
    venue.save();
    Date date1 = Date.valueOf("1965-06-15");
    Date date2 = Date.valueOf("1968-01-01");

    band.addConcert(venue, date1);
    band.addConcert(venue, date2);

  }



    // org.sql2o.Sql2oException: Error in executeUpdate, ERROR: duplicate key value violates unique constraint "unique_instance"
    // Detail: Key (band_id, venue_id, date)=(37, 6, 1968-01-01) already exists.

} // END BandTest CLASS
