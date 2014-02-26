package ch.hsr.informatik.prog1.testat2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModuleFactory {
    private static ModuleFactory instance;
    private Map<String, Module>  moduleMap = new HashMap<>();

    public static ModuleFactory getInstance() {
        if (instance == null) {
            instance = new ModuleFactory();
        }
        return instance;
    }

    private ModuleFactory() {
    }

    public Module getModule(String aModuleName) {
        Module module = moduleMap.get(aModuleName);
        if (module == null) {
            module = new Module(aModuleName);
            moduleMap.put(aModuleName, module);
        }
        return module;
    }

    public void markModuleAsVisited(Module aVisitedModule) {
        Map<String, Module> modulesWithRequirement = getModulesByDirectRequirement(aVisitedModule);
        for (Map.Entry<String, Module> module : modulesWithRequirement
                .entrySet()) {
            moduleMap.get(module.getKey()).requiredModuleSet
                    .remove(aVisitedModule);
        }
        moduleMap.remove(aVisitedModule.name);
    }

    public boolean hasUnvisitedModules() {
        return !moduleMap.isEmpty();
    }

    @Override
    public String toString() {
        return "ModuleFactory [moduleMap=" + moduleMap + "]";
    }

    public Map<String, Module> getModuleMap() {
        return moduleMap;
    }

    /**
     * Returns all modules that have the given module as a direct requirement.
     */
    public Map<String, Module> getModulesByDirectRequirement(
            Module aRequiredModule) {
        Map<String, Module> modulesWithRequiredModule = new HashMap<>();
        for (Module module : moduleMap.values()) {
            if (module.requiredModuleSet.contains(aRequiredModule)) {
                modulesWithRequiredModule.put(module.name, module);
            }
        }
        return moduleMap;
    }

    class Module {
        private String      name;
        private Set<Module> requiredModuleSet = new HashSet<>();

        private Module(String aName) {
            super();
            name = aName;
        }

        private Module(String aName, Set<Module> aRequiredModuleSet) {
            super();
            name = aName;
            requiredModuleSet = aRequiredModuleSet;
        }

        @Override
        public String toString() {
            StringBuilder moduleStringRepresentation = new StringBuilder(40);
            moduleStringRepresentation.append(name);
            moduleStringRepresentation.append(" required [ ");
            for (Module requiredModule : requiredModuleSet) {
                moduleStringRepresentation.append(requiredModule);
            }

            moduleStringRepresentation.append(" ] ");

            return moduleStringRepresentation.toString();
        }

        public String getName() {
            return name;
        }

        public Set<Module> getRequiredModuleSet() {
            return requiredModuleSet;
        }

        public boolean hasRequiredModules() {
            return requiredModuleSet.size() != 0;
        }

        public void setRequiredModuleSet(Set<Module> aRequiredModuleSet) {
            requiredModuleSet = aRequiredModuleSet;
        }
    }
}