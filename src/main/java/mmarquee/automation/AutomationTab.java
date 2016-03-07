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

package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationTab extends AutomationContainer {

    private List<AutomationTabItem> tabItems;

    /**
     * Selects the tab with the given name
     * @param name The name of the tab to select
     */
    public void selectTabPage(String name) {
        for (int count = 0; count < this.tabItems.size(); count++) {
            if (name.equals(this.tabItems.get(count).name())) {
                this.tabItems.get(count).selectItem();
            }
        }
    }

    public AutomationTab (AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        // Now get the list of tab items
        tabItems = new ArrayList<AutomationTabItem>();

        List<AutomationElement> collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.size();

        for (int count = 0; count < length; count++ ) {
            AutomationElement elem = collection.get(count);

            int retVal = elem.element.currentControlType();

            if (retVal == ControlType.TabItem) {
                this.tabItems.add(new AutomationTabItem(elem, this.uiAuto));
            }
        }
    }
}