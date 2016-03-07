/*
 * Copyright 2016 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.pattern;


import mmarquee.automation.AutomationElement;
import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 25/02/2016.
 */
public class Selection extends BasePattern {
    public List<AutomationElement> getCurrentSelection () {
        IUIAutomationElementArray collection = ((IUIAutomationSelectionPattern)pattern).getCurrentSelection();

        List<AutomationElement> list = new ArrayList<AutomationElement>();

        for(int count = 0; count < collection.length(); count++) {
            list.add(new AutomationElement(collection.getElement(count)));
        }

        return list;

    }
}
