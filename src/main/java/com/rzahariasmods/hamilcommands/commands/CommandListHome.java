package com.rzahariasmods.hamilcommands.commands;

import com.rzahariasmods.hamilcommands.player.data.PlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandListHome extends CommandBase
{
    @Override
    public String getName()
    {
        return "listhome";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "listhome";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
    {
        if (sender instanceof EntityPlayer)
            PlayerData.sendPlayerHomes((EntityPlayer) sender);
    }
}