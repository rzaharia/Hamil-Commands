package com.rzahariasmods.hamilcommands.player.data.myNBT;

import com.rzahariasmods.hamilcommands.commands.util.Teleport;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.ArrayList;
import java.util.List;

import static com.rzahariasmods.hamilcommands.player.data.myNBT.NBTUtils.getModTag;

public class HomeTag
{
    private static final int MAX_PERMITTED_HOMES = 2;
    private static final String HOME_NAME_PATTERN = "h%d_name";
    private static final String DIMENSION_ID_PATTERN = "dimension%d";
    private static final String LOCATION_X_NAME_PATTERN = "location_x%d";
    private static final String LOCATION_Y_NAME_PATTERN = "location_y%d";
    private static final String LOCATION_Z_NAME_PATTERN = "location_z%d";

    private static String[] getHomeNamesList(EntityPlayer player)
    {
        String[] result = new String[MAX_PERMITTED_HOMES];
        NBTTagCompound modTag = getModTag(player);
        for (int i = 0; i < MAX_PERMITTED_HOMES; ++i)
        {
            result[i] = "";
            if (modTag.hasKey(String.format(HOME_NAME_PATTERN, i)) && !modTag.getString(String.format(HOME_NAME_PATTERN, i)).equals(""))
                result[i] = modTag.getString(String.format(HOME_NAME_PATTERN, i));
        }
        return result;
    }

    public static void sendPlayerHomeList(EntityPlayer player)
    {
        String[] homes = getHomeNamesList(player);
        List<String> result = new ArrayList<>();
        for (String home : homes)
            if (!home.equals(""))
                result.add(home);
        StringBuilder finalResult = new StringBuilder();
        finalResult.append("Found ").append(result.size()).append(" homes");
        finalResult.append(result.size() == 0 ? "." : " : ");
        for (String aResult : result)
            finalResult.append(aResult).append(" ");
        player.sendMessage(new TextComponentString(TextFormatting.WHITE + finalResult.toString()));
    }

    public static void savePlayerHome(EntityPlayer player, String homeName)
    {
        if (getPlayerHomeNumber(player) >= MAX_PERMITTED_HOMES)
        {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "You already reached maximum number of houses!"));
            return;
        }

        if (!isHouseNameAvailable(player, homeName))
        {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "Name already used!"));
            return;
        }

        int freeLocation = getFreeHome(player);
        if (freeLocation == -1)//all ocupied
            return;

        NBTTagCompound modTag = getModTag(player);
        //if (!modTag.hasKey(String.format(HOME_NAME_PATTERN, freeLocation)) || modTag.getString(String.format(HOME_NAME_PATTERN, freeLocation)).equals(""))

        modTag.setString(String.format(HOME_NAME_PATTERN, freeLocation), homeName);

        modTag.setInteger(String.format(DIMENSION_ID_PATTERN, freeLocation), player.getEntityWorld().provider.getDimension());
        modTag.setDouble(String.format(LOCATION_X_NAME_PATTERN, freeLocation), player.posX);
        modTag.setDouble(String.format(LOCATION_Y_NAME_PATTERN, freeLocation), player.posY);
        modTag.setDouble(String.format(LOCATION_Z_NAME_PATTERN, freeLocation), player.posZ);


        player.sendMessage(new TextComponentString(TextFormatting.WHITE + "House saved successfully!"));
    }

    public static void sendPlayerHome(EntityPlayer player, String homeName)
    {
        String[] homes = getHomeNamesList(player);
        for (int i = 0; i < homes.length; i++)
        {
            String home = homes[i];
            if (home.equals(homeName))
            {
                double x, y, z;
                int dimension;
                NBTTagCompound modTag = getModTag(player);
                x = modTag.getDouble(String.format(LOCATION_X_NAME_PATTERN, i));
                y = modTag.getDouble(String.format(LOCATION_Y_NAME_PATTERN, i));
                z = modTag.getDouble(String.format(LOCATION_Z_NAME_PATTERN, i));
                dimension = modTag.getInteger(String.format(DIMENSION_ID_PATTERN, i));

                Teleport.teleportToDimension(player, dimension, x, y, z);

                return;
            }
        }
        player.sendMessage(new TextComponentString(TextFormatting.RED + "You don't a home named like that!"));
    }

    private static int getFreeHome(EntityPlayer player)
    {
        String[] homes = getHomeNamesList(player);
        for (int i = 0; i < homes.length; i++)
        {
            String home = homes[i];
            if (home.equals(""))
                return i;
        }
        return -1; //should never be reached
    }

    private static boolean isHouseNameAvailable(EntityPlayer player, String houseName)
    {
        String[] homes = getHomeNamesList(player);
        for (String home : homes)
        {
            if (home.equals(houseName))
                return false;
        }
        return true;
    }

    private static int getPlayerHomeNumber(EntityPlayer player)
    {
        int count = 0; //INTREB
        String[] homes = getHomeNamesList(player);
        for (String home : homes)
            if (!home.equals(""))
                count++;
        return count;
    }

    public static void deletePlayerHome(EntityPlayer player, String homeName)
    {
        String[] homes = getHomeNamesList(player);
        for (int i = 0; i < homes.length; i++)
        {
            String home = homes[i];
            if (home.equals(homeName))
            {
                NBTTagCompound modTag = getModTag(player);
                modTag.setString(String.format(HOME_NAME_PATTERN, i), "");
                player.sendMessage(new TextComponentString(TextFormatting.WHITE + "Home deleted successfully!"));
                return;
            }
        }
        player.sendMessage(new TextComponentString(TextFormatting.RED + "You don't a home named like that!"));
    }
}