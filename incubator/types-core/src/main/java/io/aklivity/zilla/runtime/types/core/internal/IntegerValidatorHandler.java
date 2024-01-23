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
package io.aklivity.zilla.runtime.types.core.internal;

import org.agrona.DirectBuffer;

import io.aklivity.zilla.runtime.engine.converter.function.ValueConsumer;
import io.aklivity.zilla.runtime.engine.validator.ValidatorHandler;
import io.aklivity.zilla.runtime.types.core.config.IntegerValidatorConfig;

public class IntegerValidatorHandler implements ValidatorHandler
{
    private int pendingBytes;

    public IntegerValidatorHandler(
        IntegerValidatorConfig config)
    {
    }

    @Override
    public boolean validate(
        int flags,
        DirectBuffer data,
        int index,
        int length,
        ValueConsumer next)
    {
        boolean valid;

        if ((flags & FLAGS_INIT) != 0x00)
        {
            pendingBytes = 4;
        }

        pendingBytes = pendingBytes - length;

        if ((flags & FLAGS_FIN) != 0x00)
        {
            valid = pendingBytes == 0;
        }
        else
        {
            valid = pendingBytes >= 0;
        }
        return valid;
    }
}