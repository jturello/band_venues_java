import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/recipes", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("recipes", Recipe.all());

      model.put("template", "templates/recipes.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/tags", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("tags", Tag.all());
      model.put("template", "templates/tags.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/recipes/deleteAll", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Recipe.deleteAll();
        response.redirect("/recipes");
        return null;
    });

    post("/recipes/:id/update", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        int id = Integer.parseInt(request.params("id"));
        Recipe recipe = Recipe.find(id);
        String recipeTitle = request.queryParams("updateRecipeTitle");
        String recipeIngredients = request.queryParams("updateRecipeIngredients");
        recipe.updateAll(recipeTitle, recipeIngredients);
        response.redirect("/recipes/" + id);
        return null;
    });

    get("/recipes/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Recipe recipe = Recipe.find(id);
      model.put("recipe", recipe);
      model.put("allTags", Tag.all());
      model.put("template", "templates/recipe.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/tags/deleteAll", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();
        Tag.deleteAll();
        response.redirect("/tags");
        return null;
    });

    get("/tags/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Tag tag = Tag.find(id);
      model.put("tag", tag);
      model.put("allRecipes", Recipe.all());
      model.put("template", "templates/tag.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/recipes", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String recipe_name = request.queryParams("recipe_name");
      String ingredients = request.queryParams("ingredients");
      Recipe newRecipe = new Recipe(recipe_name, ingredients);
      newRecipe.save();
      response.redirect("/recipes");
      return null;
    });

    post("/recipes/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int recipeId = Integer.parseInt(request.queryParams("recipeId"));
      Recipe recipe = Recipe.find(recipeId);
      recipe.delete();
      response.redirect("/recipes");
      return null;
    });

    post("/recipes/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int recipeId = Integer.parseInt(request.queryParams("recipe_id"));
      int tagId = Integer.parseInt(request.queryParams("tagTitle"));
      Tag tag = Tag.find(tagId);
      Recipe recipe = Recipe.find(recipeId);
      recipe.addTag(tag);
      response.redirect("/recipes/" + recipeId);
      return null;
    });

    post("/tags/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int tagId = Integer.parseInt(request.queryParams("tag_id"));
      int recipeId = Integer.parseInt(request.queryParams("recipe_id"));
      Recipe recipe = Recipe.find(recipeId);
      Tag tag = Tag.find(tagId);
      tag.addRecipe(recipe);
      response.redirect("/tags/" + tagId);
      return null;
    });

    post("/tags", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String tagTitle = request.queryParams("tagTitle");
      Tag newTag = new Tag(tagTitle);
      newTag.save();
      response.redirect("/tags");
      return null;
    });

    post("/tags/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int tagId = Integer.parseInt(request.queryParams("tagId"));
      Tag tag = Tag.find(tagId);
      tag.delete();
      response.redirect("/tags");
      return null;
    });





    //
    // post("/courses/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int courseId = Integer.parseInt(request.queryParams("course_id"));
    //   int student_id = Integer.parseInt(request.queryParams("student_id"));
    //   Course course = Course.find(courseId);
    //   Student student = Student.find(student_id);
    //   course.addStudent(student);
    //   response.redirect("/courses/" + courseId);
    //   return null;
    // });
  }

  //public static 'Returntype' 'FuncName' (Paramtype param) {}  //first business logic function

}
