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
package mmarquee.automation.pattern;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

/**
 * @author Mark Humphreys
 * Date 11/01/2017.
 *
 * Tests for the Value pattern
 *
 */
public class ValuePatternTest2 {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Mock
    IUIAutomationValuePattern rawPattern;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected=AutomationException.class)
    public void test_SetValue_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).setValue(any());

        Value pattern = new Value(rawPattern);

        pattern.setValue("VALUE-01");
    }

    @Test
    public void test_SetValue_Calls_SetValue_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 0;
            }
        }).when(rawPattern).setValue(any());

        Value pattern = new Value(rawPattern);

        pattern.setValue("VALUE-01");

        verify(rawPattern, atLeastOnce()).setValue(any());
    }

    @Test(expected=AutomationException.class)
    @Ignore("Throws Mockito exception")
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Value pattern = new Value();

        Value spyPattern = Mockito.spy(pattern);

        IUIAutomationValuePattern mockPattern = Mockito.mock(IUIAutomationValuePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any(Pointer.class));

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(any(PointerByReference.class));

        spyPattern.value();

        verify(mockPattern, atLeastOnce()).getValue(any());
    }
}
