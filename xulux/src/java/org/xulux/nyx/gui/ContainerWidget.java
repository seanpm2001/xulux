package org.xulux.nyx.gui;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Specifies a container widget.
 * It makes overriding a bit easier.
 * 
 * @author Martin van den Bemt
 * @version $Id: ContainerWidget.java,v 1.4 2003-09-23 12:29:16 mvdb Exp $
 */
public abstract class ContainerWidget extends Widget
{
    protected ArrayList widgets;
    
    /**
     * Constructor for ContainerWidget.
     * @param name
     */
    public ContainerWidget(String name)
    {
        super(name);
    }

    /**
     * @see org.xulux.nyx.gui.Widget#addChildWidget(Widget)
     */
    public void addChildWidget(Widget widget)
    {
        if (widgets == null)
        {
            widgets = new ArrayList();
        }
        if (widget != null) {
            widgets.add(widget);
            widget.setRootWidget(false);
            // add to the parent if the parent is already
            // initialized..
            if (initialized) {
                addToParent(widget);
            }
        }
    }

    /**
     * @see org.xulux.nyx.gui.Widget#canBeRootWidget()
     */
    public boolean canBeRootWidget()
    {
        return super.canBeRootWidget();
    }

    /**
     * @see org.xulux.nyx.gui.Widget#canContainChildren()
     */
    public boolean canContainChildren()
    {
        return true;
    }
    
    public ArrayList getChildWidgets()
    {
        return widgets;
    }
    /**
     * @see org.xulux.nyx.gui.Widget#initialize()
     */
    public void initializeChildren()
    {
        ArrayList widgets = getChildWidgets();
        if (widgets == null) {
            return;
        }
        Iterator iterator = widgets.iterator();
        while (iterator.hasNext())
        {
            Widget widget = (Widget)iterator.next();
            addToParent(widget);
        }
        
    }
    
    /**
     * Adds a childwidget to the parent
     * @param widget - the child widget
     */
    public abstract void addToParent(Widget widget);


    /**
     * You probably have to override this,
     * since it only destroys the children
     * and doesn't cleanup the parent
     * object, since it doesn't know about it.
     * 
     * @see org.xulux.nyx.gui.Widget#destroy()
     */
    public void destroy()
    {
        ArrayList children = getChildWidgets();
        if (children != null)
        {
            Iterator it = children.iterator();
            while (it.hasNext())
            {
                Widget cw = (Widget)it.next();
                cw.destroy();
            }
            children.clear();
            children = null;
        }
    }

}
