/*
 * Thrifty
 *
 * Copyright (c) Microsoft Corporation
 *
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * THIS CODE IS PROVIDED ON AN  *AS IS* BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, EITHER EXPRESS OR IMPLIED, INCLUDING
 * WITHOUT LIMITATION ANY IMPLIED WARRANTIES OR CONDITIONS OF TITLE,
 * FITNESS FOR A PARTICULAR PURPOSE, MERCHANTABLITY OR NON-INFRINGEMENT.
 *
 * See the Apache Version 2.0 License for specific language governing permissions and limitations under the License.
 */
package com.microsoft.thrifty.schema;

import com.microsoft.thrifty.schema.parser.FieldElement;
import com.microsoft.thrifty.schema.parser.TypeElement;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FieldTest {
    @Test
    public void requiredFields() {
        FieldElement element = fieldBuilder()
                .requiredness(Requiredness.REQUIRED)
                .build();
        Field field = new Field(element, FieldNamingPolicy.DEFAULT);
        assertTrue(field.required());
        assertFalse(field.optional());
    }

    @Test
    public void optionalFields() {
        FieldElement element = fieldBuilder()
                .requiredness(Requiredness.OPTIONAL)
                .build();
        Field field = new Field(element, FieldNamingPolicy.DEFAULT);
        assertFalse(field.required());
        assertTrue(field.optional());
    }

    @Test
    public void defaultFields() {
        FieldElement element = fieldBuilder()
                .requiredness(Requiredness.DEFAULT)
                .build();
        Field field = new Field(element, FieldNamingPolicy.DEFAULT);
        assertFalse(field.required());
        assertFalse(field.optional());
    }

    private FieldElement.Builder fieldBuilder() {
        Location location = Location.get("", "");
        return FieldElement.builder(location)
                .fieldId(1)
                .name("foo")
                .type(TypeElement.scalar(location, "i32", null));
    }
}