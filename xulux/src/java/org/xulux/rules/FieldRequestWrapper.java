/*
   $Id: FieldRequestWrapper.java,v 1.2 2004-01-28 15:02:20 mvdb Exp $
   
   Copyright 2002-2004 The Xulux Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.xulux.rules;

import org.xulux.context.PartRequest;
import org.xulux.gui.Widget;

/**
 * A wrapper (interface) around the request,
 * so we just need to pass in 1 object into
 * the parser.
 *
 * @author <a href="mailto:martin@mvdb.net">Martin van den Bemt</a>
 * @version $Id: FieldRequestWrapper.java,v 1.2 2004-01-28 15:02:20 mvdb Exp $
 */
public interface FieldRequestWrapper {

    /**
     *
     * @return the caller widget
     */
    Widget getWidget();

    /**
     *
     * @return the part request
     */
    PartRequest getPartRequest();

    /**
     *
     * @return the type of fieldRequest.
     */
    int getType();

}
