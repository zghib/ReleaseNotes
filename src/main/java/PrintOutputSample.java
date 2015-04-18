import com.solveit.impl.RTFImpl;
import com.solveit.model.JiraItem;
import com.solveit.model.PrintItem;
import com.solveit.services.RTF;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/10/13
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class PrintOutputSample {

    public static void main(String[] args ) {
        RTF rtf = new RTFImpl();
        Collection<PrintItem> sections = fakePrintData();
        rtf.generateRTF(new File("Test.rtf"), sections);
    }

    private static Collection<PrintItem> fakePrintData() {
        List<PrintItem> result = new ArrayList<PrintItem>();
        for (int i = 0; i < 3; i++) {
            PrintItem pi = new PrintItem();
            pi.setSectionHeader("Section" + i);

            List<JiraItem> jiraItems = new ArrayList<JiraItem>();
            for (int j = 0; j < 5; j++) {
                JiraItem ji = new JiraItem();
                ji.setItemKey("Key" + j);
                ji.setItemLink("http//" + j);
                ji.setItemSummary("Summary" + j);
                jiraItems.add(ji);
            }
            pi.setJiraItems(jiraItems);
            result.add(pi);
        }
        return result;
    }
}
