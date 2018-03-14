package lv.brain.gradle;

import jd.core.JavaDecompiler;
import jd.core.input.JDInput;
import jd.core.input.ZipFileInput;
import jd.core.options.DecompilerOptions;
import jd.core.options.OptionsManager;
import jd.core.output.JDOutput;
import jd.core.output.ZipOutput;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public class GradleDecompileTask extends DefaultTask {

    @TaskAction
    public void decompile() throws Exception {

        String jarFolder = "/Users/edgarsgrinvalds/Downloads/Test/lib";
        File file = new File(jarFolder);
        System.out.println(getPath());
        System.out.println(getProject().getRootDir());
        System.out.println(file);
        if(file.isDirectory()){
            System.out.println("dir");
            for (File jarFile : file.listFiles()) {
                decompileFile(jarFile);
            }


        }
        else if (file.isFile()){
            System.out.println("file");
            decompileFile(file);
        }
        else{
            System.out.println("nothing");
        }
    }

    void decompileFile(final File file){
        OptionsManager.setOptions(new DecompilerOptions() {
            @Override
            public boolean isSkipResources() {
                return false;
            }

            @Override
            public boolean isDisplayLineNumbers() {
                return true;
            }

            @Override
            public boolean isDisplayMetadata() {
                return true;
            }

            @Override
            public boolean isDiscardLocation() {
                return false;
            }

            @Override
            public boolean isEscapeUnicodeCharacters() {
                return false;
            }

            @Override
            public boolean isShowPrefixThis() {
                return true;
            }

            @Override
            public boolean isRealignLineNumbers() {
                return true;
            }

            @Override
            public boolean isShowDefaultConstructor() {
                return false;
            }

            @Override
            public boolean isMergeEmptyLines() {
                return false;
            }
        });
        JavaDecompiler javaDecompiler = new JavaDecompiler();
        JDInput jdIn = new ZipFileInput(file.getAbsolutePath());

        System.out.println(file.getAbsolutePath());
        System.out.println(file.getAbsolutePath().replaceAll(".jar", "-src.jar"));

        JDOutput jdOut = new ZipOutput(new File(file.getAbsolutePath().replaceAll(".jar", "-src.jar")));
        jdIn.decompile(javaDecompiler, jdOut);
    }
}