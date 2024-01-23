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
module io.aklivity.zilla.runtime.types.json
{
    requires io.aklivity.zilla.runtime.engine;

    requires org.leadpony.justify;

    exports io.aklivity.zilla.runtime.types.json.config;

    provides io.aklivity.zilla.runtime.engine.config.ConverterConfigAdapterSpi
        with io.aklivity.zilla.runtime.types.json.internal.config.JsonConverterConfigAdapter;

    provides io.aklivity.zilla.runtime.engine.converter.ConverterFactorySpi
        with io.aklivity.zilla.runtime.types.json.internal.JsonConverterFactorySpi;

    provides io.aklivity.zilla.runtime.engine.config.ValidatorConfigAdapterSpi
        with io.aklivity.zilla.runtime.types.json.internal.config.JsonValidatorConfigAdapter;
}