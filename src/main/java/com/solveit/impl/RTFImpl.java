package com.solveit.impl;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import com.solveit.model.JiraItem;
import com.solveit.model.PrintItem;
import com.solveit.services.RTF;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: izghibarta
 * Date: 7/10/13
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class RTFImpl implements RTF {
    @Override
    public void generateRTF(File file, Collection<PrintItem> items) {
        Document document = new Document();
        try {
            BaseFont bf = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontH = new Font(bf,15);
            Font fontL = new Font(bf,10);
            fontL.setColor(Color.BLUE);
            Font fontT = new Font(bf,10);
            RtfWriter2.getInstance(document, new FileOutputStream(file));
            document.open();
            for (PrintItem item : items){
                Paragraph p = new Paragraph(item.getSectionHeader(), fontH);
                document.add( Chunk.NEWLINE );
                document.add(p);
                for(JiraItem issue : item.getJiraItems()){
                    Anchor anchor = new Anchor(issue.getItemKey());
                    anchor.setReference(issue.getItemLink());
                    anchor.setFont(fontL);
                    document.add(anchor);
                    document.add(new Paragraph(issue.getItemSummary(), fontT));
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
        document.close();
    }
}
