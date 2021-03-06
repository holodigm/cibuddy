/*
 * Copyright (C) 2012 Mirko Jahn <mirkojahn@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cibuddy.core.build.configuration;

/**
 * In case an IProjectSetup should be updated regularly, this must be implemented.
 * 
 * @author Mirko Jahn <mirkojahn@gmail.com>
 */
public interface Triggerable {
    String UPDATE_TRIGGER_TYPE = "updateTriggerType";
    
    void updateIndicator();
    
}
