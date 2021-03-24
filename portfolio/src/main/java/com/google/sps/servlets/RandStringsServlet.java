package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/rand-strings")
public class RandStringsServlet extends HttpServlet {

  static final long serialVersionUID = 0;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> stringSkills = new ArrayList<String>();
    stringSkills.add("Python");
    stringSkills.add("Java");
    stringSkills.add("HTML");
    stringSkills.add("CSS");
    stringSkills.add("JavaScript");
    stringSkills.add("React");

    String json = convertToJsonUsingGson(stringSkills);

    response.setContentType("application/json;");
    response.getWriter().println(json);
  }

  /**
   * Converts a ArrayList<String> instance into a JSON string using the Gson library. Note: We first added
   * the Gson library dependency to pom.xml.
   */
  private String convertToJsonUsingGson(ArrayList<String> strings) {
    Gson gson = new Gson();
    String json = gson.toJson(strings);
    return json;
  }
}
