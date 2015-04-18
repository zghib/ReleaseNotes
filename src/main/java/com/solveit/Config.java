package com.solveit;
import com.solveit.model.Section;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/9/13
 * Time: 4:42 PM
 * To change this template use File | Settings | File Templates.
 */
public class Config  {

    private File outputFile;
    private String jiraUri;
    private String username;
    private String password;
    private Collection<Section> sections;

    public Config(){
       readFromXML();
    }

    public Config(File outputFile, String jiraUri, String username, String password, Collection<Section> sections) {
        this.outputFile = outputFile;
        this.jiraUri = jiraUri;
        this.username = username;
        this.password = password;
        this.sections = sections;
    }

    public void readFromXML(){
        List<Section> sections = new ArrayList<Section>();
        try {
            File file = new File("properties.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            String user = doc.getElementsByTagName("userName").item(0).getFirstChild().getNodeValue();
            String passw = doc.getElementsByTagName("password").item(0).getFirstChild().getNodeValue();
            String outputFile = doc.getElementsByTagName("outputFile").item(0).getFirstChild().getNodeValue();
            String jiraUri = doc.getElementsByTagName("jiraUri").item(0).getFirstChild().getNodeValue();

            NodeList nodeLst = doc.getElementsByTagName("section");
            for (int s = 0; s < nodeLst.getLength(); s++) {
                Node fstNode = nodeLst.item(s);
                if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element fstElmnt = (Element) fstNode;
                    NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("name");
                    Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
                    NodeList fstNm = fstNmElmnt.getChildNodes();
                    NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("jql");
                    Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
                    NodeList lstNm = lstNmElmnt.getChildNodes();
                    Section sec = new Section(((Node) fstNm.item(0)).getNodeValue(),((Node) lstNm.item(0)).getNodeValue());
                    sections.add(sec);
                }

            }

            this.outputFile = new File(outputFile);
            this.jiraUri = jiraUri;
            this.username = user;
            this.password = passw;
            this.sections = sections;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public String getJiraUri() {
        return jiraUri;
    }

    public void setJiraUri(String jiraUri) {
        this.jiraUri = jiraUri;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Section> getSections() {
        return sections;
    }

    public void setSections(Collection<Section> sections) {
        this.sections = sections;
    }
}
