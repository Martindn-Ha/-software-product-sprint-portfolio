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
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Sanitize user input to remove HTML tags and JavaScript.
    String email = Jsoup.clean(request.getParameter("email-input"), Safelist.none());
    String msg = Jsoup.clean(request.getParameter("msg-input"), Safelist.none());
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contact");
    FullEntity taskEntity =
    Entity.newBuilder(keyFactory.newKey())
        .set("email", email)
        .set("message", msg)
        .set("timestamp", timestamp)
        .build();
        datastore.put(taskEntity);
    //Redirect client to front page
    response.sendRedirect("https://mha-sps-summer22.wl.r.appspot.com");
  }
}