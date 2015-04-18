package com.solveit.services;

import com.solveit.model.PrintItem;

import java.io.File;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/9/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public interface RTF {
    void generateRTF(File outputFile, Collection<PrintItem> sections);
}
