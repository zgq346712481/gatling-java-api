/*
 * Copyright 2020 Vincent Galloy
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
package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckMaterializer;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;

public interface ToCheckBuilder<T, P, X> {

  CheckBuilderWrapper<T, P, X> toCheckBuilder();

  CheckMaterializer<T, HttpCheck, Response, P> getMaterializer();
}
