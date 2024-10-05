package net.citybuild.backend.initializer;

import net.citybuild.CityBuild;
import net.citybuild.backend.utility.Utility;
import net.citybuild.backend.utility.command.ExecutableCommand;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.reflections.Reflections;

public class Initializer {

    public Initializer() {
        new Reflections("net.citybuild.backend.listener")
                .getSubTypesOf(Listener.class)
                .forEach(all -> {
                    try {
                        Bukkit.getPluginManager().registerEvents(all.newInstance(), CityBuild.getINSTANCE());
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

        new Reflections("net.citybuild.frontend.command")
                .getSubTypesOf(ExecutableCommand.class)
                .forEach(all -> {
                    try {
                        ExecutableCommand commandInstance = all.newInstance();
                        Utility.getCommandMap().register("minetropia", commandInstance);
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

    }
}
