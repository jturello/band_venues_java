import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Tag {
  private String title;
  private int id;

  public Tag (String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public static  List<Tag> all() {
    String sql = "SELECT * FROM tags";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Tag.class);
    }
  }

  @Override
  public boolean equals(Object otherTag) {
    if (!(otherTag instanceof Tag)) {
      return false;
    } else {
      Tag newTag = (Tag) otherTag;
      return this.getTitle().equals(newTag.getTitle()) &&
      this.getId() == newTag.getId();
    }
  }

  public void save() {
    String sql = "INSERT INTO tags (title) VALUES (:title)";
     try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
        .addParameter("title", title)
        .executeUpdate()
        .getKey();
    }
  }

  public static Tag find(int id) {
    String sql = "SELECT id, title FROM tags WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Tag tag = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Tag.class);
      return tag;
    }
  }

  public void update(String title) {
    String sql ="UPDATE tags SET title = :title WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("title", title)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void delete() {
    String sqlJoin ="DELETE FROM recipes_tags WHERE tag_id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .addParameter("id", id)
        .executeUpdate();
    }
    String sql ="DELETE FROM tags WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .addParameter("id", id)
        .executeUpdate();
    }
  }

  public static void deleteAll() {
    String sqlJoin ="DELETE FROM recipes_tags";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sqlJoin)
        .executeUpdate();
    }
    String sql ="DELETE FROM tags ";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
        .executeUpdate();
    }
  }

  public void addRecipe (Recipe recipe) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO recipes_tags (recipe_id, tag_id) VALUES (:recipe_id, :tag_id)";
      con.createQuery(sql)
      .addParameter("tag_id", this.getId())
      .addParameter("recipe_id", recipe.getId())
      .executeUpdate();
    }
  }

  public List<Recipe> getRecipes() {
    try(Connection con = DB.sql2o.open()) {

      String sql = "SELECT recipes.* FROM tags " +
      "JOIN recipes_tags ON (tags.id = tag_id) " +
      "JOIN recipes ON (recipe_id = recipes.id) " +
      "WHERE tags.id = :id";
      List<Recipe> recipes = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetch(Recipe.class);
      return recipes;
    }
  }
  }
