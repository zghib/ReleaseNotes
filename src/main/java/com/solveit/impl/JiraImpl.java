package com.solveit.impl;
import com.solveit.Config;
import com.solveit.model.JiraItem;
import com.solveit.services.Jira;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MultivaluedMap;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/9/13
 * Time: 4:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class JiraImpl implements Jira {

    private Config config;

    public JiraImpl(Config config){
        this.config = config;
    }

    @Override
    public Collection<JiraItem> searchResult(String search) {
        List<JiraItem> result = new ArrayList<JiraItem>();
        Client client = Client.create();
        MultivaluedMap queryParams = new MultivaluedMapImpl();
        queryParams.add("jql", search);
        queryParams.add("fields", "summary");
        WebResource webResource = client
                .resource(config.getJiraUri()+"rest/api/2/search").queryParams(queryParams);

        ClientResponse response =  webResource.header("Authorization", "Basic " + getAuth()).type("application/json")
                .accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }

        String output = response.getEntity(String.class);

        JSONObject json = null;
        try {
            json = new JSONObject(output);
            JSONArray arr = json.getJSONArray("issues");

            for(int i=0; i<arr.length(); i++){
                JiraItem ji = new JiraItem();
                JSONObject jo = arr.getJSONObject(i);
                String key = jo.get("key").toString();
                ji.setItemKey(key);
                ji.setItemLink(config.getJiraUri()+"/browse/"+key);
                JSONObject j1 = new JSONObject(jo.get("fields").toString());
                ji.setItemSummary(j1.get("summary").toString());
                result.add(ji);
            }

        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return result;

    }

    private String getAuth() {
        return new String(Base64.encode(config.getUsername()+":"+ config.getPassword()));
    }
}
