/* Copyright 2004 Acegi Technology Pty Limited
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

package net.sf.acegisecurity;

import java.util.Iterator;


/**
 * Returns a new run-as identity if configuration attribute RUN_AS is found.
 * The new identity is simply an empty {@link MockRunAsAuthenticationToken}.
 *
 * @author Ben Alex
 * @version $Id$
 */
public class MockRunAsManager implements RunAsManager {
    //~ Methods ================================================================

    public Authentication buildRunAs(Authentication authentication,
        Object object, ConfigAttributeDefinition config) {
        Iterator iter = config.getConfigAttributes();

        while (iter.hasNext()) {
            ConfigAttribute attr = (ConfigAttribute) iter.next();

            if (this.supports(attr)) {
                Authentication response = new MockRunAsAuthenticationToken();
                response.setAuthenticated(true);

                return response;
            }
        }

        return null;
    }

    public boolean supports(ConfigAttribute attribute) {
        if ("RUN_AS".equals(attribute.getAttribute())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean supports(Class clazz) {
        return true;
    }
}
