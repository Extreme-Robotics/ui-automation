/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
package mmarquee.demo;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

/**
 * Created by Mark Humphreys on 26/02/2016.
 *
 * Test the automation wrapper on Excel.
 */
public class TestMainExcel extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // 0. Load excel
            try {
                // Start the application
                application = automation.launchOrAttach("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.EXE");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach");
            }

            // 1. Load a file in excel

            // 2. Get the sheet

            // window - "Book1 - Excel"
            // . Panel (classname = XLDESK)
            // - Panel ("Book1")
            // -- panel("Sheet Navigation Bar")
            // ---- tab (0)
            //


            AutomationWindow window = application.getWindow("Book1 - Excel");
            logger.info("Window - " + window.name());

            AutomationPanel panel10 = window.getPanel(10);
            logger.info("Panel10 - " + panel10.getClassName());

            AutomationPanel panelBook1 = panel10.getPanel("Book1");

            logger.info("PanelBook1 - " + panelBook1.name());
            logger.info("PanelBook1 - " + panelBook1.getClassName());

            AutomationCustom panel = panelBook1.getCustom("Sheet Navigation Bar");
            logger.info("Panel - " + panel.name());
            logger.info("Panel - " + panel.getClassName());

            AutomationTab tab = panel.getTab(0);
            logger.info("Tab - " + tab.name());

            AutomationTabItem tabItem = tab.getTabItems().get(0);
            logger.info("TabItem - " + tabItem.name());
            logger.info("TabItem - " + tabItem.getClassName());

            tabItem.selectItem();

            AutomationDataGrid grid = panelBook1.getDataGrid(0);
            logger.info("Grid - " + grid.name());

            // 3. Get some data
            AutomationDataGridCell cell = grid.getItem(0,0);
            logger.info(cell.name());
            logger.info(cell.value());

            // 4. Set some data
            List<AutomationDataGridCell> headers = grid.getColumnHeaders();

            for(AutomationDataGridCell header : headers) {
                logger.info(header.value());
            }

            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}