package ch.hsr.informatik.prog1.testat2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ch.hsr.informatik.prog1.testat2.ModuleFactory.Module;

public class CatalogueReader implements AutoCloseable {
    private BufferedReader reader;

    public CatalogueReader(String filePath) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(filePath));
    }

    // null if end of input reached
    public Module readNextLine() throws IOException {
        ModuleFactory moduleFactory = ModuleFactory.getInstance();
        ModuleFactory.Module module = null;
        String line;
        if ((line = reader.readLine()) != null) {
            String[] moduleArray = line.split(" ");
            if (line != null) {
                module = moduleFactory.getModule(moduleArray[0]);

                Set<Module> requiredModulesSet = new HashSet<>(
                        moduleArray.length - 1);
                for (int i = 1; i < moduleArray.length; i++) {
                    requiredModulesSet.add(moduleFactory
                            .getModule(moduleArray[i]));
                }
                module.setRequiredModuleSet(requiredModulesSet);
            }
        }
        return module;
    }

    public Map<String, Module> readAll() throws IOException {
        while (readNextLine() != null) {
        }

        return ModuleFactory.getInstance().getModuleMap();
    }

    @Override
    public void close() throws Exception {
        reader.close();
    }
}
