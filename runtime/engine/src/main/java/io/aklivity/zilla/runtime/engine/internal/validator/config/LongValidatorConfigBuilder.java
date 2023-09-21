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

import java.util.function.Function;

import io.aklivity.zilla.runtime.engine.config.ConfigBuilder;

public class LongValidatorConfigBuilder<T> extends ConfigBuilder<T, LongValidatorConfigBuilder<T>>
{
    private final Function<LongValidatorConfig, T> mapper;

    LongValidatorConfigBuilder(
        Function<LongValidatorConfig, T> mapper)
    {
        this.mapper = mapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Class<LongValidatorConfigBuilder<T>> thisType()
    {
        return (Class<LongValidatorConfigBuilder<T>>) getClass();
    }

    @Override
    public T build()
    {
        return mapper.apply(new LongValidatorConfig());
    }
}