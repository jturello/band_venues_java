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



}

  //
  // @Test
  // public void rootTest() {
  //     goTo("http://localhost:4567/");
  //     assertThat(pageSource()).contains("Recipe Box");
  // }
  //
  //
  // @Test
  // public void addRecipe() {
  //   goTo("http://localhost:4567/recipes");
  //   fill("#recipe_name").with("Tacos");
  //   fill("#ingredients").with("Beef");
  //   submit("#addRecipe");
  //   assertThat(pageSource()).contains("Tacos");
  //   assertThat(pageSource()).contains("Beef");
  // }
  //
  // @Test
  // public void addTag() {
  //   goTo("http://localhost:4567/tags");
  //   fill("#tagTitle").with("History");
  //   submit("#addTagBtn");
  //   assertThat(pageSource()).contains("History");
  // }
  //
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
  // public void deleteRecipe() {
  //   Tag newTag = new Tag("Mexican");
  //   newTag.save();
  //   Recipe newRecipe = new Recipe("Tacos");
  //   newRecipe.save();
  //   newRecipe.addTag(newTag);
  //   String recipeId = "#" + newRecipe.getId();
  //   goTo("http://localhost:4567/recipes");
  //   submit(recipeId);
  //   assertThat(pageSource()).doesNotContain(newRecipe.getTitle());
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
