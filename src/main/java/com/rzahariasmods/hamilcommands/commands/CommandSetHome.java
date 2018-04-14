package com.rzahariasmods.hamilcommands.commands;

import com.rzahariasmods.hamilcommands.player.data.PlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandSetHome extends CommandBase
{
    @Override
    public String getName()
    {
        return "sethome";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "sethome <name>";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
    {
        if (args.length < 1)
            throw new CommandException("Not enouth parameters! Missing home name");
        if (sender instanceof EntityPlayer)
            PlayerData.savePlayerHome((EntityPlayer) sender, args[0]);
    }
}