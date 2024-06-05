package com.example.wild_restful;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Path("/reviews")
public class ReviewService {

    private List<String> comments;

    public ReviewService() {
        comments = loadComments();
    }

    private List<String> loadComments() {
        List<String> commentsList = new ArrayList<>();
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("comments.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find comments.properties");
                return commentsList;
            }
            properties.load(input);
            for (String key : properties.stringPropertyNames()) {
                commentsList.add(properties.getProperty(key));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return commentsList;
    }

    @GET
    @Path("/viewpoint")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getViewpointReviews(@QueryParam("viewpointId") int viewpointId) {
        List<Map<String, String>> reviews = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            Map<String, String> review = new HashMap<>();
            String randomComment = comments.get(random.nextInt(comments.size()));
            review.put("comment", randomComment);
            reviews.add(review);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("viewpointId", viewpointId);
        result.put("reviews", reviews);
        return Response.ok(result).build();
    }
}
