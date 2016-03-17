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


  // @Test
  // public void addTagToRecipe() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   String recipePath = String.format("http://localhost:4567/recipes/%d", newRecipe.getId());
  //   goTo(recipePath);
  //   assertThat(pageSource()).contains("Mexican");
  //   assertThat(pageSource()).contains("Tacos");
  // }
  //
  // @Test
  // public void addRecipeToTag() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   String tagPath = String.format("http://localhost:4567/tags/%d", newTag.getId());
  //   goTo(tagPath);
  //   assertThat(pageSource()).contains("Mexican");
  //   assertThat(pageSource()).contains("Tacos");
  // }

  //
  // @Test
  // public void deleteTag() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   String tagId = "#" + newTag.getId();
  //   goTo("http://localhost:4567/tags");
  //   submit(tagId);
  //   assertThat(pageSource()).doesNotContain(newTag.getTitle());
  //   assertThat(pageSource()).doesNotContain("404");
  // }
  //
  // @Test
  // public void assignTagToRecipe() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   // newRecipe.addTag(newTag);
  //   goTo("http://localhost:4567/recipes/" + newRecipe.getId());
  //   fillSelect("#tagTitle").withIndex(0);
  //   submit("#assignTagBtn");
  //   assertThat(pageSource()).contains("<a href=\"/tags/" + newTag.getId() + "\">");
  //
  // }
  //
  // @Test
  // public void assignRecipeToTag() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   // newRecipe.addTag(newTag);
  //   goTo("http://localhost:4567/tags/" + newTag.getId());
  //   fillSelect("#recipe_id").withIndex(0);
  //   submit("#assignRecipeBtn");
  //   assertThat(pageSource()).contains("<a href=\"/recipes/" + newRecipe.getId() + "\">");
  //
  // }
  //
  // @Test
  // public void deleteAllRecipes_displaysNoRecipesOnRecipePage() {
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   goTo("http://localhost:4567/recipes");
  //   submit("#deleteAllRecipesBtn");
  //   assertThat(pageSource()).doesNotContain("Tacos");
  //   assertThat(pageSource()).doesNotContain("Error");
  // }
  //
  // @Test
  // public void updateAllRecipes_updatesNameAndIngredients() {
  //   Recipe newRecipe = new Recipe("Tacos", "Beef");
  //   newRecipe.save();
  //
  //   goTo("http://localhost:4567/recipes/" + newRecipe.getId());
  //   fill("#updateRecipeTitle").with("UpdatedTitle");
  //   fill("#updateRecipeIngredients").with("Yak Butt");
  //   submit("#updateRecipe");
  //   assertThat(pageSource()).contains("UpdatedTitle");
  //   assertThat(pageSource()).contains("Yak Butt");
  // }
  //
} // END OF AppTest CLASS
