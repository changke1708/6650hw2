package com.example;

import com.example.dal.UtilDao;
import com.example.model.StepCounts;
import com.example.dal.StepCountsDao;

import java.sql.SQLException;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path("/stepcounter")
public class Stepcounter {
    private StepCountsDao stepCountsDao = StepCountsDao.getInstance();
    private UtilDao utilDao = UtilDao.getInstance();


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return ("Deployed");
    }

    @POST
    @Path("/{userID}/{dayId}/{timeInterval}/{stepCount}")
    @Consumes(MediaType.TEXT_PLAIN)
    public String postData(@PathParam("userID") String userID,
                           @PathParam("dayId") String dayId,
                           @PathParam("timeInterval") String timeInterval,
                           @PathParam("stepCount") String stepCount) throws SQLException {
        StepCounts stepCounts = new StepCounts(Integer.parseInt(userID),
                Integer.parseInt(dayId),
                Integer.parseInt(timeInterval),
                Integer.parseInt(stepCount));
        stepCountsDao.create(stepCounts);
        return "posted!";
    }

    @GET
    @Path("/current/{userID}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getByUser(@PathParam("userID") String userID) throws SQLException {
        int sum = stepCountsDao.getStepCountCurrent(Integer.parseInt(userID));
        return sum;
    }

    @GET
    @Path("/range/{userID}/{startDay}/{numDays}")
    @Produces(MediaType.TEXT_PLAIN)
    public int[] getByRange(@PathParam("userID") String dayId,
                            @PathParam("startDay") String startDay,
                            @PathParam("numDays") String numDays) throws SQLException {
      int[] stepCounts = stepCountsDao.getStepCountByRange(Integer.parseInt(dayId),
          Integer.parseInt(startDay), Integer.parseInt(numDays));
      return stepCounts;
    }

    @GET
    @Path("/single/{userID}/{dayId}")
    @Produces(MediaType.TEXT_PLAIN)
    public int getByDay(@PathParam("userID") String userID,
                           @PathParam("dayId") String dayId) throws SQLException {
        int sum = stepCountsDao.getStepCountByDay(Integer.parseInt(userID), Integer.parseInt(dayId));
        return sum;
    }

    @DELETE
    @Path("/delete")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteTable() throws SQLException {
        utilDao.cleanTable();
        return "Deleted!";
    }
}
