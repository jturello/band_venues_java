import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class BandTest {


  // @ClassRule
  // public static ServerRule server = new ServerRule();

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
    Band newBand = new Band("The Ramons");
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

  // @Test
  // public void updateIngredients_updatesIngredientsOfObject() {
  //   Band newBand = new Band("Sally", "Tomatoes");
  //   newBand.save();
  //   newBand.updateIngredients("1901/01/01");
  //   assertEquals(Band.all().get(0).getIngredients(), ("1901/01/01"));
  // }
  //
  // @Test
  // public void updateInstructions_updatesInstructionsOfObject() {
  //   Band newBand = new Band("Sally", "Tomatoes", "Bake 350");
  //   newBand.save();
  //   newBand.updateInstructions("1901/01/01");
  //   assertEquals(Band.all().get(0).getInstructions(), ("1901/01/01"));
  // }
  //
  // @Test
  // public void updateRating_updatesRatingOfObject() {
  //   Band newBand = new Band("Sally", "Tomatoes", "Bake 350", 1);
  //   newBand.save();
  //   newBand.updateRating(4);
  //   assertEquals(Band.all().get(0).getRating(), (4));
  // }
  //
  // @Test
  // public void deleteBand() {
  //   Band newBand = new Band("BLT");
  //   newBand.save();
  //   newBand.delete();
  //   assertEquals(Band.all().size(), 0);
  // }
  //
  // @Test
  // public void addConcert_addsConcertToBand() {
  //   Band newBand = new Band("BLT");
  //   newBand.save();
  //
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //
  //   newBand.addConcert(newConcert);
  //   Concert savedConcert = newBand.getConcerts().get(0);
  //   assertTrue(newConcert.equals(savedConcert));
  // }
  //
  // @Test
  // public void getConcerts_getsBandsConcertsByBandID() {
  //   Band newBand = new Band("BLT");
  //   newBand.save();
  //
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //
  //   newBand.addConcert(newConcert);
  //   List savedConcerts = newBand.getConcerts();
  //   assertEquals(savedConcerts.size(), 1);
  // }
  //
  // @Test
  // public void deleteAll_deletesAllBandsAndConcerts() {
  //     Band firstBand = new Band("BLT");
  //     Band secondBand = new Band("Taco");
  //     firstBand.save();
  //     secondBand.save();
  //     Concert firstConcert = new Concert("firstConcert");
  //     firstBand.addConcert(firstConcert);
  //     Band.deleteAll();
  //     assertEquals(Band.all().size(), 0);
  // }
  //
  // @Test
  // public void deleteAll_deletesConcertAssociations () {
  //     Band firstBand = new Band("BLT");
  //     firstBand.save();
  //     int BandId = firstBand.getId();
  //     Concert firstConcert = new Concert("firstConcert");
  //     firstBand.addConcert(firstConcert);
  //     Band.deleteAll();
  //     assertEquals(firstConcert.getBands().size(), 0);
  // }

} // END BandTest CLASS
