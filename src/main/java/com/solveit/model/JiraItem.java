package com.solveit.model;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/10/13
 * Time: 9:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class JiraItem {
    private String itemKey;
    private String itemLink;
    private String itemSummary;

    public String getItemKey() {
        return itemKey;
    }

    public void setItemKey(String itemKey) {
        this.itemKey = itemKey;
    }

    public String getItemLink() {
        return itemLink;
    }

    public void setItemLink(String itemLink) {
        this.itemLink = itemLink;
    }

    public String getItemSummary() {
        return itemSummary;
    }

    public void setItemSummary(String itemSummary) {
        this.itemSummary = itemSummary;
    }
}
