/*
 * Copyright (C) 2011, the original authors                                       
 *                                                                                
 * ====================================================================           
 * Licensed under the Apache License, Version 2.0 (the "License");                
 * you may not use this file except in compliance with the License.               
 * You may obtain a copy of the License at                                        
 *                                                                                
 * http://www.apache.org/licenses/LICENSE-2.0                                     
 *                                                                                
 * Unless required by applicable law or agreed to in writing, software            
 * distributed under the License is distributed on an "AS IS" BASIS,              
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.       
 * See the License for the specific language governing permissions and            
 * limitations under the License.                                                 
 * ====================================================================
 */

package org.jclouds.karaf.commands.blobstore;

import org.apache.felix.gogo.commands.Command;
import org.apache.felix.gogo.commands.Option;
import org.jclouds.apis.Apis;
import org.jclouds.blobstore.BlobStore;
import org.jclouds.providers.Providers;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Command(scope = "jclouds", name = "blobstore-service-destroy", description = "Destroys a BlobStore service.", detailedDescription = "classpath:blobstore-service-destroy.txt")
public class BlobStoreDestroyCommand extends BlobStoreServiceCommand {

    @Override
    protected Object doExecute() throws Exception {
        if (provider == null && api == null) {
            System.err.println("You need to specify at least a valid provider or api.");
            return null;
        }

        Configuration configuration = findOrCreateFactoryConfiguration(configAdmin, "org.jclouds.blobstore", provider, api);
        if (configuration != null) {
            configuration.delete();
        } else {
            System.out.println("No service found for provider / api");
        }
        return null;
    }

    public ConfigurationAdmin getConfigAdmin() {
        return configAdmin;
    }

    public void setConfigAdmin(ConfigurationAdmin configAdmin) {
        this.configAdmin = configAdmin;
    }
}