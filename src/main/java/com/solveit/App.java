package com.solveit;

import com.solveit.impl.JiraImpl;
import com.solveit.impl.RTFImpl;
import com.solveit.services.Jira;
import com.solveit.services.RTF;

public class App {

    public static void main(String [] args){
        Config config = new Config();
        ReleaseNotes rn = new ReleaseNotes(config);
        rn.process();
    }
}
