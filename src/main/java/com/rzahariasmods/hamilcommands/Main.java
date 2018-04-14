package com.rzahariasmods.hamilcommands;

import com.rzahariasmods.hamilcommands.proxy.CommonProxy;
import com.rzahariasmods.hamilcommands.util.Reference;
import com.rzahariasmods.hamilcommands.util.handlers.RegistryHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main
{
    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event)
    {
    }

    @Mod.EventHandler
    public static void serverInit(FMLServerStartingEvent event)
    {
        RegistryHandler.serverRegistries(event);
    }
}