/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.jobson.auth;

import com.github.jobson.api.config.CustomAuthenticatorConfig;
import org.junit.Test;

import static com.github.jobson.other.TestHelpers.createTypicalAuthBootstrap;
import static com.github.jobson.other.TestHelpers.generateClassName;

public final class CustomAuthenticatorConfigTest {

    @Test(expected = NullPointerException.class)
    public void testCreateAuthFilterThrowsIfClassNameIsNull() {
        final CustomAuthenticatorConfig config =
                new CustomAuthenticatorConfig(null);
        config.createAuthFilter(createTypicalAuthBootstrap());
    }

    @Test(expected = RuntimeException.class)
    public void testCreateAuthFilterIfClassNameDoesNotExistOnClassPath() {
        final CustomAuthenticatorConfig config =
                new CustomAuthenticatorConfig(generateClassName());
        config.createAuthFilter(createTypicalAuthBootstrap());
    }

    @Test(expected = RuntimeException.class)
    public void testCreateAuthFilterIfClassDoesNotDeriveFromAuthenticationConfig() {
        final CustomAuthenticatorConfig config =
                new CustomAuthenticatorConfig(Object.class.getName());
        config.createAuthFilter(createTypicalAuthBootstrap());
    }

    @Test
    public void testCreateAuthFilterDoesNotThrowIfClassDoesDeriveFromAuthenticationConfig() {
        final CustomAuthenticatorConfig config =
                new CustomAuthenticatorConfig(NullCustomAuthConfig.class.getName());
        config.createAuthFilter(createTypicalAuthBootstrap());
    }
}