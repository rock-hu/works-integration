/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.works.concurrency.pipeline;

/**
 * @author Marius Bogoevici
 */
public class FinishedCar {

    private long serialNumber;


    public FinishedCar(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public long getSerialNumber() {
        return serialNumber;
    }
}
