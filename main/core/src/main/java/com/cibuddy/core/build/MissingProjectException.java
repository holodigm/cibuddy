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
package com.cibuddy.core.build;

/**
 *
 * @author Mirko Jahn <mirkojahn@gmail.com>
 * @version 1.0
 * @since 1.0
 */
public class MissingProjectException extends ProjectSetupException {
    public static final String MISSING_PROJECT_TYPE = "missing_project";
    
    public MissingProjectException(String projectName) {
        super(MISSING_PROJECT_TYPE, "Couldn't retrieve project for name: "+projectName);
    }
    public MissingProjectException(String projectName, Exception e) {
        super(MISSING_PROJECT_TYPE, "Couldn't retrieve project for name: "+projectName, e);
    }
}
