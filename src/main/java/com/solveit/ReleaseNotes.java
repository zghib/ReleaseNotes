package com.solveit;

import com.solveit.impl.JiraImpl;
import com.solveit.impl.RTFImpl;
import com.solveit.model.JiraItem;
import com.solveit.model.PrintItem;
import com.solveit.model.Section;
import com.solveit.services.Jira;
import com.solveit.services.RTF;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReleaseNotes
{
    Config config;
    Jira jira;
    RTF rtf;

    public ReleaseNotes(Config config){
        this.config = config;
        this.jira = new JiraImpl(config);
        this.rtf = new RTFImpl();
    }

    public void process(){
        List<PrintItem> printItems = new ArrayList<PrintItem>();
        for (Section section: config.getSections()){
            PrintItem pi = new PrintItem();
            pi.setSectionHeader(section.getName());
            String searchJQL = section.getJql();
            System.out.println(searchJQL);
            Collection<JiraItem>  jiraItems = jira.searchResult(searchJQL);
            pi.setJiraItems(jiraItems);
            printItems.add(pi);
        }
        rtf.generateRTF(config.getOutputFile(), printItems);
    }
}
