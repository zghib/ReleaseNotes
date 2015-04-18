package com.solveit.services;

import com.solveit.model.JiraItem;

import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/9/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Jira {
    Collection<JiraItem> searchResult(String search);
}
