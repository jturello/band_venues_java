import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ConcertTest {

  // @ClassRule
  // public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();




} // END ConcertTest CLASS

  // @Test
  // public void all_emptyAtFirst() {
  //   assertEquals(Concert.all().size(), 0);
  // }
  //
  // @Test
  // public void equals_returnsTrueIfTitlesAreTheSame() {
  //   Concert firstConcert = new Concert("Mexican");
  //   Concert secondConcert = new Concert("Mexican");
  //   assertTrue(firstConcert.equals(secondConcert));
  // }
  //
  // @Test
  // public void save_addsInstanceOfConcertToDatabase() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //   Concert savedConcert = Concert.all().get(0);
  //   assertTrue(newConcert.equals(savedConcert));
  // }
  //
  // @Test
  // public void save_assignsIdToObject() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //   Concert savedConcert = Concert.all().get(0);
  //   assertEquals(newConcert.getId(), savedConcert.getId());
  // }
  //
  // @Test
  // public void find_locatesAllInstancesOfClassInDatabaseUsingId() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //   Concert savedConcert = Concert.find(newConcert.getId());
  //   assertTrue(newConcert.equals(savedConcert));
  // }
  //
  // @Test
  // public void update_updatesTitleOfObject() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //   newConcert.update("Italian");
  //   assertEquals(Concert.all().get(0).getTitle(), ("Italian"));
  // }
  //
  // @Test
  // public void delete_deleteConcertObject() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //   newConcert.delete();
  //   assertEquals(Concert.all().size(), 0);
  // }
  //
  // @Test
  // public void addConcert_addsConcertToConcert() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //
  //   Concert newConcert = new Concert("Enchilada");
  //   newConcert.save();
  //
  //   newConcert.addConcert(newConcert);
  //   Concert savedConcert = newConcert.getConcerts().get(0);
  //   assertTrue(newConcert.equals(savedConcert));
  // }
  //
  // @Test
  // public void getConcert_getsConcertByConcertID() {
  //   Concert newConcert = new Concert("Mexican");
  //   newConcert.save();
  //
  //   Concert newConcert = new Concert("Tacos");
  //   newConcert.save();
  //
  //   newConcert.addConcert(newConcert);
  //   List savedConcert = newConcert.getConcerts();
  //   assertEquals(savedConcert.size(), 1);
  // }
  //
  // @Test
  // public void deleteAll_deletesAllConcerts() {
  //     Concert firstConcert = new Concert("BLT");
  //     Concert secondConcert = new Concert("Taco");
  //     firstConcert.save();
  //     secondConcert.save();
  //     Concert firstConcert = new Concert("firstConcert");
  //     firstConcert.addConcert(firstConcert);
  //     Concert.deleteAll();
  //     assertEquals(Concert.all().size(), 0);
  // }
  //
  // @Test
  // public void deleteAll_deletesAllConcertAssociations() {
  //     Concert firstConcert = new Concert("BLT");
  //     Concert secondConcert = new Concert("Taco");
  //     firstConcert.save();
  //     secondConcert.save();
  //     Concert firstConcert = new Concert("firstConcert");
  //     firstConcert.addConcert(firstConcert);
  //     Concert.deleteAll();
  //     assertEquals(firstConcert.getConcerts().size(), 0);
  // }
