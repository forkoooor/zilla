/*
 * Copyright 2021-2023 Aklivity Inc.
 *
 * Aklivity licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.aklivity.zilla.runtime.engine.internal.validator.config;

import static java.util.function.Function.identity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;

import org.junit.Before;
import org.junit.Test;

import io.aklivity.zilla.runtime.engine.config.ValidatorConfig;

public class LongValidatorConfigAdapterTest
{
    private Jsonb jsonb;

    @Before
    public void initJson()
    {
        JsonbConfig config = new JsonbConfig()
            .withAdapters(new LongValidatorConfigAdapter());
        jsonb = JsonbBuilder.create(config);
    }

    @Test
    public void shouldReadLongValidator()
    {
        // GIVEN
        String json =
            "{" +
                "\"type\":\"long\"" +
            "}";

        // WHEN
        LongValidatorConfig longValidator = jsonb.fromJson(json, LongValidatorConfig.class);

        // THEN
        assertThat(longValidator, not(nullValue()));
        assertThat(longValidator.type, equalTo("long"));
    }

    @Test
    public void shouldWriteLongValidator()
    {
        // GIVEN
        String expectedJson =
            "{" +
                "\"type\":\"long\"" +
            "}";
        ValidatorConfig longValidator = new LongValidatorConfigBuilder<>(identity()).build();

        // WHEN
        String json = jsonb.toJson(longValidator);

        // THEN
        assertThat(json, not(nullValue()));
        assertThat(json, equalTo(expectedJson));
    }
}