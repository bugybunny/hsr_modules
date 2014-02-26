package ch.hsr.informatik.prog1.testat2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ch.hsr.informatik.prog1.testat2.ModuleFactory.Module;

public class TimetablePlanning {
    private static Map<String, Set<Module>> termModuleMap = new HashMap<String, Set<Module>>();
    private static final String             TERM          = "Semester";

    /** Utility class. */
    private TimetablePlanning() {
    }

    private static void planTerm(Collection<Module> someRemainingModules) {
        List<Module> modulesWithoutRequirementList = new ArrayList<>();

        for (Module module : someRemainingModules) {
            if (!module.hasRequiredModules()) {
                modulesWithoutRequirementList.add(module);
            }
        }
        addModulesToTerm(modulesWithoutRequirementList);

    }

    private static void addModulesToTerm(Collection<Module> someModules) {
        String term = TERM + " " + (termModuleMap.size() + 1);
        Set<Module> moduleSet = termModuleMap.get(term);
        if (moduleSet == null) {
            moduleSet = new HashSet<>(someModules);
            termModuleMap.put(term, moduleSet);
        } else {
            moduleSet.addAll(someModules);
        }

        for (Module visitedModule : someModules) {
            ModuleFactory.getInstance().markModuleAsVisited(visitedModule);
        }
    }

    public static void main(String[] args) {
        new TimetablePlanning();
        try (CatalogueReader reader = new CatalogueReader("StudyCatalogue.txt")) {

            Map<String, Module> allModules = reader.readAll();
            while (ModuleFactory.getInstance().hasUnvisitedModules()) {
                planTerm(allModules.values());
            }

            for (Map.Entry<String, Set<Module>> modulesPerTerm : termModuleMap
                    .entrySet()) {
                System.out.print(modulesPerTerm.getKey() + ": ");
                for (Module module : modulesPerTerm.getValue()) {
                    System.out.print(module.getName() + " ");
                }
                long foo = System.currentTimeMillis();
                System.out.println(System.currentTimeMillis() - foo);
            }

        }
        catch (Exception anEx) {
            anEx.printStackTrace();
        }

    }
}
