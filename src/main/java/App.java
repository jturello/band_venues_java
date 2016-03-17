import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.util.*;
import java.sql.Date;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      List<Band> bands = Band.all();

      model.put("bands", bands);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/bands", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String name = request.queryParams("name");
      String genre = request.queryParams("genre");

      Band band = new Band(name, genre);
      band.save();

      response.redirect("/");
      return null;
    });

    post("/bands/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int band_id = Integer.parseInt(request.queryParams("band_id"));
      Band band = Band.find(band_id);
      band.delete();

      response.redirect("/");
      return null;
    });

    get("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int id = Integer.parseInt(request.params(":id"));
      Band band = Band.find(id);
      // List venues = ;

      model.put("band", band);
      model.put("venues", Venue.all());
      model.put("concerts", band.getConcerts());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/bands/deleteAll", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();

        Band.deleteAll();
        response.redirect("/");
        return null;
    });


    post("/bands/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int bandId = Integer.parseInt(request.queryParams("band_id"));
      int venueId = Integer.parseInt(request.queryParams("venue_id"));
      Date concertDate = Date.valueOf(request.queryParams("concertDate"));
      Venue venue = Venue.find(venueId);
      Band band = Band.find(bandId);
      band.addConcert(venue, concertDate);
      response.redirect("/bands/" + bandId);
      return null;
    });


    get("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      model.put("venues", Venue.all());

      model.put("template", "templates/venues.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/venues", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      String name = request.queryParams("name");
      String location = request.queryParams("location");
      Venue venue = new Venue(name, location);
      venue.save();

      if(request.queryParams("requestFrom").equals("band_vtl")) {
          int band_id = Integer.parseInt(request.queryParams("band_id"));
          response.redirect("/bands/" + band_id);
          return null;
      };

      response.redirect("/venues");
      return null;
    });

    get("/venues/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Venue venue = Venue.find(id);
      model.put("venue", venue);

      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    post("/venues/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int venue_id = Integer.parseInt(request.queryParams("venue_id"));
      Venue venue = Venue.find(venue_id);
      venue.delete();
      response.redirect("/venues");
      return null;
    });

    post("/venues/deleteAll", (request, response) -> {
        HashMap<String, Object> model = new HashMap<String, Object>();

        Venue.deleteAll();
        response.redirect("/venues");
        return null;
    });

    post("/concerts/:id/delete", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();

      int concert_id = Integer.parseInt(request.params(":id"));
      int bandId = Integer.parseInt(request.queryParams("band_id"));
      Band band = Band.find(bandId);
      band.deleteConcert(concert_id);

      response.redirect("/bands/" + bandId); //pass venue - what does /bands/:id need
      return null;
    });


    // get("/bands", (request, response) -> {
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
  } // END MAIN METHOD

  //public static 'Returntype' 'FuncName' (Paramtype param) {}  //first business logic function

} // END APP CLASS
