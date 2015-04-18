package com.solveit.model;
import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/10/13
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class PrintItem {
    private String SectionHeader;
    private Collection<JiraItem> jiraItems;


    public String getSectionHeader() {
        return SectionHeader;
    }

    public void setSectionHeader(String sectionHeader) {
        SectionHeader = sectionHeader;
    }


    public Collection<JiraItem> getJiraItems() {
        return jiraItems;
    }

    public void setJiraItems(Collection<JiraItem> jiraItems) {
        this.jiraItems = jiraItems;
    }
}
