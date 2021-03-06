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
package com.cibuddy.web.ui.impl;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class ActivatorSample implements BundleActivator, ServiceTrackerCustomizer {

    private ServiceTracker httpTracker;
    private String serviceName = "/tests";
    private BundleContext ctx = null;

    /**
     * {@inheritDoc}
     *
     * @see
     * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
     */
    public void start(BundleContext context) throws Exception {
        ctx = context;
        httpTracker =
                new ServiceTracker(context, HttpService.class.getName(), this);
        httpTracker.open();

    }

    /**
     * {@inheritDoc}
     *
     * @see
     * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
     */
    public void stop(BundleContext context) throws Exception {
        if (httpTracker != null) {
            HttpService serv = ((HttpService) httpTracker.getService());
            if (serv != null) {
                serv.unregister(serviceName);
            }
            httpTracker.close();
        }
    }

    public Object addingService(ServiceReference reference) {
        Hashtable<String, String> initparams = new Hashtable<String, String>();
        initparams.put("name", serviceName);
        javax.servlet.Servlet myServlet = new HttpServlet() {

            private static final long serialVersionUID = -7398914113448648745L;
            @SuppressWarnings("unused")
            String name = "/test";

            public void init(ServletConfig config) {
                this.name = (String) config.getInitParameter("name");
            }

            public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException {
                rsp.setContentType("text/plain");
                rsp.getWriter().println("Hello World! - by OSGi Inspector");
            }
        };
        HttpService http = null;
        try {
            http = ((HttpService) ctx.getService(reference));
            http.registerServlet(serviceName, myServlet, initparams, http.createDefaultHttpContext());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (NamespaceException e) {
            e.printStackTrace();
        }

        return http;
    }

    public void modifiedService(ServiceReference reference, Object service) {
        // do nothing
    }

    public void removedService(ServiceReference reference, Object service) {
        // nothing todo		
    }
}
