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

package org.works.business;

import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.Publisher;
import org.springframework.stereotype.Component;

/**
 * @author Marius Bogoevici
 */
@Component
public class SimpleFlightStatusService implements FlightStatusService {

    @Publisher(channel = "statisticsChannel")
    public FlightStatus updateStatus(@Payload FlightDelayEvent flightDelayEvent) {
        // update flight status information in the database
        // return updated status
    	return new FlightStatus();
    }
}
