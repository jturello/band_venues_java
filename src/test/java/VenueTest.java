import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class VenueTest {

  // @ClassRule
  // public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();


  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfVenueNameAndLocationMatch_true() {
    Venue firstVenue = new Venue("someVenue", "someLocation");
    Venue secondVenue = new Venue("someVenue", "someLocation");
    assertTrue(firstVenue.equals(secondVenue));
  }

  @Test
  public void equals_returnsFalse_whenLocationsDiffer_true() {
    Venue firstVenue = new Venue("newVenue", "newLocation");
    Venue secondVenue = new Venue("newVenue", "differentLocation");
    assertFalse(firstVenue.equals(secondVenue));
  }

  @Test
  public void save_addsInstanceOfVenueToDatabase() {
    Venue newVenue = new Venue("newVenue", "newLocation");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertTrue(newVenue.equals(savedVenue));
  }

  @Test
  public void save_assignsIdToObject() {
    Venue newVenue = new Venue("newVenue", "newLocation");
    newVenue.save();
    Venue savedVenue = Venue.all().get(0);
    assertEquals(newVenue.getId(), savedVenue.getId());
  }


  @Test
  public void find_returnsCorrectObject_true() {
    Venue firstVenue = new Venue("venueOne", "locationOne");
    firstVenue.save();
    Venue secondVenue = new Venue("venueTwo", "locationTwo");
    secondVenue.save();
    assertEquals(firstVenue, Venue.find(firstVenue.getId()));
  }

  @Test
  public void delete_deletesSelectedVenueFromDatabase_true() {
    Venue venue = new Venue("newVenue", "newLocation");
    venue.save();
    venue.delete();
    assertEquals(0, Venue.all().size());
  }

  @Test
  public void delete_doesNotDeletesNonSelectedVenueFromDatabase_true() {
    Venue firstVenue = new Venue("venueOne", "locationOne");
    Venue secondVenue = new Venue("venueTwo", "locationTwo");
    firstVenue.save();
    secondVenue.save();
    firstVenue.delete();
    assertEquals(1, Venue.all().size());
    assertEquals(secondVenue, Venue.all().get(0));
  }

  @Test
  public void deleteAll_deletesAllVenuesAndConcerts() {
    Venue firstVenue = new Venue("venueOne", "locationOne");
    Venue secondVenue = new Venue("venueTwo", "locationTwo");
    firstVenue.save();
    secondVenue.save();
    Venue.deleteAll();
    assertEquals(0, Venue.all().size());
  }

} // END OF VenueTest CLASS
