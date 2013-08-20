/*
 *  Licensed to the Paulo Marques under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package pmarques.util.io;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.StringReader;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import pmarques.util.io.BoundedBufferedReader;

/**
 * Tests for generating keys
 */
@RunWith(JUnit4.class)
public class BoundedBufferReaderTest {
    @Before
    public void setup() {
    }

    //------------------------------------------------------------------------------------------------------------------

    @Test
    public void basicLineTest() throws Exception {
        String[] lineSeparators = { "\n", "\r", "\r\n" };

        for (int s1L=1; s1L<10; s1L++) {
            for (int s2L=1; s2L<10; s2L++) {
                for (int s3L=1; s3L<10; s3L++) {
                    for (String lineSeparator: lineSeparators) {
                        String s1 = generateString(s1L);
                        String s2 = generateString(s2L);
                        String s3 = generateString(s3L);
                        String myString = s1 + lineSeparator + s2 + lineSeparator + s3;

                        for (int bufferSize=1; bufferSize<myString.length()+1; bufferSize++) {
                            BoundedBufferedReader br = new BoundedBufferedReader(new StringReader(myString), bufferSize);

                            String readS1 = br.readLine(s1.length());
                            String readS2 = br.readLine(s2.length());
                            String readS3 = br.readLine(s3.length());

                            assertEquals(readS1, s1);
                            assertEquals(readS2, s2);
                            assertEquals(readS3, s3);
                        }
                    }
                }
            }
        }
    }

    @Test
    public void tooLongTest() throws Exception {
        String[] lineSeparators = { "\n", "\r", "\r\n" };

        for (int s1L=1; s1L<30; s1L++) {
            for (int limit=1; limit<s1L; limit++) {
                for (String lineSeparator: lineSeparators) {
                    String s1 = generateString(s1L);
                    String myString = s1 + lineSeparator + s1;

                    for (int bufferSize=1; bufferSize<myString.length()+1; bufferSize++) {
                        BoundedBufferedReader br = new BoundedBufferedReader(new StringReader(myString), bufferSize);

                        boolean fine = false;
                        try {
                            String line = br.readLine(limit);
                        }
                        catch (LineToLongException e) {
                            fine = true;
                        }
                        assertTrue(fine);
                    }
                }
            }
        }
    }

    private static String generateString(int length) {
        StringBuffer b = new StringBuffer();
        for (int i=0; i<length; i++) {
            b.append('x');
        }
        return b.toString();
    }
}
