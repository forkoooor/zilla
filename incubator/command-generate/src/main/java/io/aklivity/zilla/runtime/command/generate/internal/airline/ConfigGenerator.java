/*
 * Copyright 2021-2023 Aklivity Inc
 *
 * Licensed under the Aklivity Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 *   https://www.aklivity.io/aklivity-community-license/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */
package io.aklivity.zilla.runtime.command.generate.internal.airline;

import static org.agrona.LangUtil.rethrowUnchecked;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.json.bind.Jsonb;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import io.aklivity.zilla.runtime.engine.config.ConverterConfig;
import io.aklivity.zilla.runtime.types.core.config.IntegerConverterConfig;
import io.aklivity.zilla.runtime.types.core.config.StringConverterConfig;

public abstract class ConfigGenerator
{
    protected static final String INLINE_CATALOG_NAME = "catalog0";
    protected static final String INLINE_CATALOG_TYPE = "inline";
    protected static final String APPLICATION_JSON = "application/json";
    protected static final String VERSION_LATEST = "latest";
    protected static final Pattern JSON_CONTENT_TYPE = Pattern.compile("^application/(?:.+\\+)?json$");

    protected final Map<String, ConverterConfig> converters = Map.of(
        "string", StringConverterConfig.builder().build(),
        "integer", IntegerConverterConfig.builder().build()
    );
    protected final Matcher jsonContentType = JSON_CONTENT_TYPE.matcher("");

    public abstract String generate();

    protected static String writeSchemaYaml(
        Jsonb jsonb,
        YAMLMapper yaml,
        Object schema)
    {
        String result = null;
        try
        {
            String schemaJson = jsonb.toJson(schema);
            JsonNode json = new ObjectMapper().readTree(schemaJson);
            result = yaml.writeValueAsString(json);
        }
        catch (JsonProcessingException ex)
        {
            rethrowUnchecked(ex);
        }
        return result;
    }

    protected final String unquoteEnvVars(
        String yaml,
        List<String> unquotedEnvVars)
    {
        for (String envVar : unquotedEnvVars)
        {
            yaml = yaml.replaceAll(
                Pattern.quote(String.format("\"${{env.%s}}\"", envVar)),
                String.format("\\${{env.%s}}", envVar)
            );
        }
        return yaml;
    }
}
