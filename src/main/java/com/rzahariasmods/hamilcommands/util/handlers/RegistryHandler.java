package com.rzahariasmods.hamilcommands.util.handlers;

import com.rzahariasmods.hamilcommands.commands.CommandDelHome;
import com.rzahariasmods.hamilcommands.commands.CommandHomeHome;
import com.rzahariasmods.hamilcommands.commands.CommandListHome;
import com.rzahariasmods.hamilcommands.commands.CommandSetHome;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class RegistryHandler
{
    public static void serverRegistries(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandSetHome());
        event.registerServerCommand(new CommandListHome());
        event.registerServerCommand(new CommandHomeHome());
        event.registerServerCommand(new CommandDelHome());
    }
}
