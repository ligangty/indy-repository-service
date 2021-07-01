/**
 * Copyright (C) 2011-2021 Red Hat, Inc. (https://github.com/Commonjava/service-parent)
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
package org.commonjava.indy.service.repository.model;

import org.apache.commons.io.IOUtils;
import org.commonjava.indy.service.repository.model.pkg.PackageTypeConstants;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.Charset;

import static org.commonjava.indy.service.repository.model.StoreType.hosted;
import static org.commonjava.indy.service.repository.model.pkg.PackageTypeConstants.PKG_TYPE_MAVEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.fail;

public class ModelJSONTest
        extends AbstractSerializatinTest
{

    String loadJson( final String resource )
            throws Exception
    {
        final InputStream is = Thread.currentThread()
                                     .getContextClassLoader()
                                     .getResourceAsStream( resource );
        if ( is == null )
        {
            fail( "Cannot find classpath resource: " + resource );
        }

        return IOUtils.toString( is, Charset.defaultCharset() );
    }

    @Test
    public void deserializeHostedRepoWithObjKey()
            throws Exception
    {
        final String json = loadJson( "hosted-with-storage-objkey.json" );
        System.out.println( json );
        final HostedRepository repo = mapper.readValue( json, HostedRepository.class );
        System.out.println( repo );
        assertThat( repo.getPackageType(), is( PKG_TYPE_MAVEN ) );
        assertThat( repo.getType(), is( hosted ) );
    }

    @Test
    public void deserializeHostedRepoWithStringKey()
            throws Exception
    {
        final String json = loadJson( "hosted-with-storage-stringkey.json" );
        System.out.println( json );
        final HostedRepository repo = mapper.readValue( json, HostedRepository.class );
        System.out.println( repo );
        assertThat( repo.getPackageType(), is( PKG_TYPE_MAVEN ) );
        assertThat( repo.getType(), is( hosted ) );
    }
}
