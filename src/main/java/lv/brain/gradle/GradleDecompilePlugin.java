package lv.brain.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GradleDecompilePlugin implements Plugin<Project> {
    static final String TASK_NAME = "decompile";

    @Override
    public void apply(Project target) {
        //target.getExtensions().create(TASK_NAME, GradleDecompileExtension.class);
        target.getTasks().create(TASK_NAME, GradleDecompileTask.class);
    }
}