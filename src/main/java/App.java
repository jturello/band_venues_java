import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // get("/", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("template", "templates/index.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/bands", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("bands", Band.all());
    //
    //   model.put("template", "templates/bands.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // get("/concerts", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   model.put("concerts", Concert.all());
    //   model.put("template", "templates/concerts.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/bands/deleteAll", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     Band.deleteAll();
    //     response.redirect("/bands");
    //     return null;
    // });
    //
    // post("/bands/:id/update", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     int id = Integer.parseInt(request.params("id"));
    //     Band band = Band.find(id);
    //     String bandTitle = request.queryParams("updateBandTitle");
    //     String bandIngredients = request.queryParams("updateBandIngredients");
    //     band.updateAll(bandTitle, bandIngredients);
    //     response.redirect("/bands/" + id);
    //     return null;
    // });
    //
    // get("/bands/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int id = Integer.parseInt(request.params("id"));
    //   Band band = Band.find(id);
    //   model.put("band", band);
    //   model.put("allConcerts", Concert.all());
    //   model.put("template", "templates/band.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/concerts/deleteAll", (request, response) -> {
    //     HashMap<String, Object> model = new HashMap<String, Object>();
    //     Concert.deleteAll();
    //     response.redirect("/concerts");
    //     return null;
    // });
    //
    // get("/concerts/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int id = Integer.parseInt(request.params("id"));
    //   Concert concert = Concert.find(id);
    //   model.put("concert", concert);
    //   model.put("allBands", Band.all());
    //   model.put("template", "templates/concert.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());
    //
    // post("/bands", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //
    //   String band_name = request.queryParams("band_name");
    //   String ingredients = request.queryParams("ingredients");
    //   Band newBand = new Band(band_name, ingredients);
    //   newBand.save();
    //   response.redirect("/bands");
    //   return null;
    // });
    //
    // post("/bands/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int bandId = Integer.parseInt(request.queryParams("bandId"));
    //   Band band = Band.find(bandId);
    //   band.delete();
    //   response.redirect("/bands");
    //   return null;
    // });
    //
    // post("/bands/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int bandId = Integer.parseInt(request.queryParams("band_id"));
    //   int concertId = Integer.parseInt(request.queryParams("concertTitle"));
    //   Concert concert = Concert.find(concertId);
    //   Band band = Band.find(bandId);
    //   band.addConcert(concert);
    //   response.redirect("/bands/" + bandId);
    //   return null;
    // });
    //
    // post("/concerts/:id", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int concertId = Integer.parseInt(request.queryParams("concert_id"));
    //   int bandId = Integer.parseInt(request.queryParams("band_id"));
    //   Band band = Band.find(bandId);
    //   Concert concert = Concert.find(concertId);
    //   concert.addBand(band);
    //   response.redirect("/concerts/" + concertId);
    //   return null;
    // });
    //
    // post("/concerts", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   String concertTitle = request.queryParams("concertTitle");
    //   Concert newConcert = new Concert(concertTitle);
    //   newConcert.save();
    //   response.redirect("/concerts");
    //   return null;
    // });
    //
    // post("/concerts/:id/delete", (request, response) -> {
    //   HashMap<String, Object> model = new HashMap<String, Object>();
    //   int concertId = Integer.parseInt(request.queryParams("concertId"));
    //   Concert concert = Concert.find(concertId);
    //   concert.delete();
    //   response.redirect("/concerts");
    //   return null;
    // });





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
