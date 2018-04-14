package com.rzahariasmods.hamilcommands.commands;

import com.rzahariasmods.hamilcommands.player.data.PlayerData;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandDelHome extends CommandBase
{
    @Override
    public String getName()
    {
        return "delhome";
    }

    @Override
    public String getUsage(ICommandSender sender)
    {
        return "/delhome <home name>";
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
            PlayerData.deletePlayerHome((EntityPlayer) sender, args[0]);
    }
}