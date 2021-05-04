package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

    /**
     * Default serial UID
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Get the value entered in the form and timestamp of submission
        String textValue = request.getParameter("text-input");
        long timestamp = System.currentTimeMillis();

        // Store submission in datastore
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("PortfolioSubmission");
        FullEntity messageEntity =
            Entity.newBuilder(keyFactory.newKey())
                .set("message", textValue)
                .set("timestamp", timestamp)
                .build();
        datastore.put(messageEntity);

        // Print the value so you can see it in the server logs.
        System.out.println("You submitted: " + textValue);

        // Write the value to the response so the user can see it.
        response.getWriter().println("You submitted: " + textValue);

        // Tells the client which URL to visit next
        // (NOTE: user will not be able to see printed response from 
        // previous getWriter() call if this line is active)
        response.sendRedirect("index.html");
    }
}