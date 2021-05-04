package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.PortfolioSubmission;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/list-submissions")
public class ListSubmissionsServlet extends HttpServlet {

    static final long serialVersionUID = 0;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        Query<Entity> query =
            Query.newEntityQueryBuilder().setKind("PortfolioSubmission").setOrderBy(OrderBy.desc("timestamp")).build();
        QueryResults<Entity> results = datastore.run(query);

        List<PortfolioSubmission> submissions = new ArrayList<>();
        while (results.hasNext()) {
            Entity entity = results.next();

            long id = entity.getKey().getId();
            String message = entity.getString("message");
            long timestamp = entity.getLong("timestamp");

            PortfolioSubmission submission = new PortfolioSubmission(id, message, timestamp);
            submissions.add(submission);
        }

        Gson gson = new Gson();

        response.setContentType("application/json;");
        response.getWriter().println(gson.toJson(submissions));
    }
}
