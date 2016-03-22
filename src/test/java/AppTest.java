import org.fluentlenium.adapter.FluentTest;
import static org.junit.Assert.*;
import org.junit.*;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
      return webDriver;
  }

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @ClassRule
  public static ServerRule server = new ServerRule();


  @Test
  public void rootTest() {
      goTo("http://localhost:4567/");
      assertThat(pageSource()).contains("Add a Band");
  }

  @Test
  public void addBand_addsBand() {
    goTo("http://localhost:4567/");
    fill("#name").with("newBand");
    fill("#genre").with("newGenre");
    submit("#addBandBtn");
    assertThat(pageSource()).contains("newBand");
  }

  @Test
  public void addBand_addsGenre() {
    goTo("http://localhost:4567/");
    fill("#name").with("newBand");
    fill("#genre").with("newGenre");
    submit("#addBandBtn");
    click("a", withText("newBand"));
    assertThat(pageSource()).contains("newGenre");
  }

  @Test
  public void goToVenueListLink_navigatesToVenueList_true() {
    goTo("http://localhost:4567/");
    click("a", withText("Go to Venue List"));
    assertThat(pageSource()).contains("Venue List");
  }

  @Test
  public void deleteAllBands_deletesAllBands_true() {
    goTo("http://localhost:4567/");
    fill("#name").with("firstBand");
    fill("#genre").with("firstGenre");
    submit("#addBandBtn");
    fill("#name").with("secondBand");
    fill("#genre").with("secondGenre");
    submit("#addBandBtn");
    click("#deleteAllBandsBtn");
    assertThat(pageSource()).doesNotContain("firstBand");
    assertThat(pageSource()).doesNotContain("secondBand");
  }

  @Test
  public void clickBandLink_navigatesToBandPage_true() {
    goTo("http://localhost:4567/");
    fill("#name").with("firstBand");
    fill("#genre").with("firstGenre");
    submit("#addBandBtn");
    click("a", withText("firstBand"));
    assertThat(pageSource()).contains("Concerts/venues the band has played");
  }

  @Test
  public void addVenue_fromBandPageRedisplaysBandPage_true() {
    goTo("http://localhost:4567/");
    fill("#name").with("firstBand");
    fill("#genre").with("firstGenre");
    submit("#addBandBtn");
    click("a", withText("firstBand"));
    fill("#name").with("Red Rocks Amphitheater");
    fill("#location").with("Denver");
    submit("#addVenueBtn");
    assertThat(pageSource()).contains("Concerts/venues the band has played");
  }

    @Test
    public void addVenue_fromVenuesPageRedisplaysVenuesPage_true() {
      goTo("http://localhost:4567/venues");
      fill("#name").with("Red Rocks Amphitheater");
      fill("#location").with("Denver");
      submit("#addVenueBtn");
      assertThat(pageSource()).contains("Venue List");
    }

    @Test
    public void addVenue_addedVenueDisplaysInVenuelist_true() {
      goTo("http://localhost:4567/venues");
      fill("#name").with("newVenue");
      fill("#location").with("someLocation");
      submit("#addVenueBtn");
      assertEquals(find("#venueList").find("li").getText(), "newVenue");
    }

} // END OF AppTest CLASS
