/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
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
package net.oneandone.maven.plugins.fossjar;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.inject.Inject;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Goal which touches a timestamp file.
 */
@Mojo(name = "touch", defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class MyMojo extends AbstractMojo {

    /**
     * Location of the file.
     */
    @Parameter(property = "project.build.directory", required = true, readonly = true)
    private File outputDirectory;

    public MyMojo() {
        super();
    }

    /**
     * Just for tests.
     * @param outputDirectory 
     */
    MyMojo(final File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    @Override
    public void execute() throws MojoExecutionException {
        touch();
    }

    private void touch() throws MojoExecutionException {
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        final File touch = new File(outputDirectory, "touch.txt");

        try {
            final FileWriter w = new FileWriter(touch);
            try {
                w.write("touch.txt");
            }
            finally {
                w.close();
            }
        } catch (IOException e) {
            throw new MojoExecutionException("Error creating file " + touch, e);
        }
    }
}
