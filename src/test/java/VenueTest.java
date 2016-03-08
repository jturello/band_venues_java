import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

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
    assertTrue(firstVenue.getName().equals(secondVenue.getName()));
  }

} // END OF VenueTest CLASS
