package com.example;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.client.ClientBuilder;
public class WebClient {
    private String REST_URL;
    private Client client = ClientBuilder.newClient();

    public WebClient(String url) {
        this.REST_URL = url;
    }

    public String postStepCount(int userId, int dayId, int timeInterval, int stepCount){
        String postStepCountURI = REST_URL + "/" + userId + "/" + dayId + "/" + timeInterval + "/" + stepCount;
        return client.target(postStepCountURI)
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.entity("", MediaType.TEXT_PLAIN), String.class);
    }

    public int getSingleDay(int userId, int dayId){
        String getSingleDayURI = REST_URL + "/single/" + userId + "/" + dayId;
        return client.target(getSingleDayURI)
                .request()
                .get(Integer.class);
    }

    public int getCurrentDay(int userId){
        String getCurrentDayURI = REST_URL + "/current/" + userId;
        return client.target(getCurrentDayURI)
                .request()
                .get(Integer.class);
    }

    public void deleteTable(){
        String deleteTableURI = REST_URL + "/delete";
        client.target(deleteTableURI)
                .request()
                .delete();
    }
}
