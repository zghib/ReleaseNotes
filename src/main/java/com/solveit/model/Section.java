package com.solveit.model;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/9/13
 * Time: 5:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Section {

    private String name;
    private String jql;

    public Section(){

    }

    public Section(String name, String jql) {
        this.name = name;
        this.jql = jql;
    }

    public String getJql() {
        return jql;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setJql(String jql){
        this.jql = jql;
    }
}
