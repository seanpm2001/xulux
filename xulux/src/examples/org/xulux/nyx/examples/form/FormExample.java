/*
 $Id: FormExample.java,v 1.3 2002-11-19 20:45:06 mvdb Exp $

 Copyright 2002 (C) The Xulux Project. All Rights Reserved.
 
 Redistribution and use of this software and associated documentation
 ("Software"), with or without modification, are permitted provided
 that the following conditions are met:

 1. Redistributions of source code must retain copyright
    statements and notices.  Redistributions must also contain a
    copy of this document.
 
 2. Redistributions in binary form must reproduce the
    above copyright notice, this list of conditions and the
    following disclaimer in the documentation and/or other
    materials provided with the distribution.
 
 3. The name "xulux" must not be used to endorse or promote
    products derived from this Software without prior written
    permission of The Xulux Project.  For written permission,
    please contact martin@mvdb.net.
 
 4. Products derived from this Software may not be called "xulux"
    nor may "xulux" appear in their names without prior written
    permission of the Xulux Project. "xulux" is a registered
    trademark of the Xulux Project.
 
 5. Due credit should be given to the Xulux Project
    (http://xulux.org/).
 
 THIS SOFTWARE IS PROVIDED BY THE XULUX PROJECT AND CONTRIBUTORS
 ``AS IS'' AND ANY EXPRESSED OR IMPLIED WARRANTIES, INCLUDING, BUT
 NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL
 THE XULUX PROJECT OR ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 OF THE POSSIBILITY OF SUCH DAMAGE.
 
 */
package org.xulux.nyx.examples.form;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.xulux.nyx.context.ApplicationContext;
import org.xulux.nyx.context.ApplicationPart;
import org.xulux.nyx.examples.datamodel.Test;
import org.xulux.nyx.examples.datamodel.TestContained;
import org.xulux.nyx.global.Dictionary;
import org.xulux.nyx.swing.BaseForm;
import org.xulux.nyx.swing.SimpleForm;
import org.xulux.nyx.swing.factories.FormFactory;
import org.xulux.nyx.swing.factories.GuiField;
import org.xulux.nyx.swing.factories.TestFactory;
import org.xulux.nyx.utils.Resources;

/**
 * A simple example of a form
 * 
 * @author <a href="mailto:martin@mvdb.net">Martin van den Bemt</a>
 * @version $Id: FormExample.java,v 1.3 2002-11-19 20:45:06 mvdb Exp $
 */
public class FormExample
{
    
    private static String FIELD1VALUE = "Field1Value";
    private static String FIELD2VALUE = "Field2Value";
    private static String FIELD3VALUE = "Field3Value";
    private static String FIELD4VALUE = "Field4Value";
    private static String FIELD5VALUE = "Field5Value";
    private static String FORM_NAME = "TestForm";
    

    /**
     * Constructor for FormExample.
     */
    public FormExample()
    {
    }
    
    public void oldTestStuff()
    {
        Dictionary dictionary = Dictionary.getInstance();
        dictionary.initialize(this.getClass().getClassLoader().getResourceAsStream("dictionary.xml"));
        // instantiate the context
/*        SimpleForm form =
            (SimpleForm) FormFactory.getForm(
                createDefaultTestObject(),
                FORM_NAME);
*/                
//       GuiField.setDefaultLabelPostfix(":");
        SimpleForm form =  (SimpleForm) FormFactory.getDefaultForm(createDefaultTestObject(), BaseForm.USEINTERNALFORM);
        System.out.println("Form : "+form);
        JFrame frame = new JFrame("FormExample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.addToWindow(frame);
        frame.setSize(frame.getPreferredSize());
        frame.show();
    }
    
    public void withTwoFormsAndDictionary()
    {
        // this is bad, but for now mandatory...
        ApplicationContext.getInstance();
        // first initialize the dictionary (not really necessary though
/*        Dictionary dictionary = Dictionary.getInstance();
        String dict = "nl/tocure/dbc/DBCDictionary.xml";
        dictionary.initialize(this.getClass().getClassLoader().getResourceAsStream(dict));
        */
        // TODO: set some defaults (should be in GuiDefaults.xml...)
        String xml = "org/xulux/nyx/examples/form/Part.xml";
        InputStream stream = getClass().getClassLoader().getResourceAsStream(xml);
        ApplicationPart part = TestFactory.getForm(stream, new Object());
        ArrayList widgets = part.getWidgets();
        Iterator it = widgets.iterator();
        JPanel panel = (JPanel)part.getRootWidget();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.blue),"te sluiten iniitele DBC"));
        part.activate();
        JFrame frame = new JFrame("DBC TEST");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.show();
        Dimension dim = ((Component)part.getRootWidget()).getPreferredSize();
        dim.width += frame.getBounds().getWidth();
        dim.height += frame.getBounds().getHeight();
        frame.setSize(dim.width,dim.height);
        frame.getContentPane().add((Component)part.getRootWidget());
        frame.pack();
    }
    

    public static void main(String[] args)
    {
        try
        {
            new FormExample().withTwoFormsAndDictionary();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
            System.exit(0);
        }
    }
    
    private org.xulux.nyx.examples.datamodel.Test createDefaultTestObject()
    {
        Test test = new Test();
        test.setField1(FIELD1VALUE);
        test.setField2(FIELD2VALUE);
        test.setField3(FIELD3VALUE);
        test.setField4(FIELD4VALUE);
        TestContained c = new TestContained();
        c.setField11(FIELD1VALUE);
        c.setField12(FIELD2VALUE);
        c.setField13(FIELD3VALUE);
        test.setContained(c);
        return test;
    }
    
}
