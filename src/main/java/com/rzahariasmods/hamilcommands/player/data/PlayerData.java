package com.rzahariasmods.hamilcommands.player.data;

import com.rzahariasmods.hamilcommands.player.data.myNBT.HomeTag;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerData
{
    public static void savePlayerHome(EntityPlayer player, String homeName)
    {
        HomeTag.savePlayerHome(player, homeName);
    }

    public static void sendPlayerHomes(EntityPlayer player)
    {
        HomeTag.sendPlayerHomeList(player);
    }

    public static void teleportPlayerHome(EntityPlayer player,String homeName)
    {
        HomeTag.sendPlayerHome(player,homeName);
    }

    public static void deletePlayerHome(EntityPlayer player,String homeName)
    {
        HomeTag.deletePlayerHome(player,homeName);
    }

//    public void test()
//    {
//        EntityPlayer player = null;
//        NBTTagCompound persistTag = null;
//        if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG))
//            persistTag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
//        else
//        {
//            persistTag = new NBTTagCompound();
//            player.getEntityData().setTag(player.PERSISTED_NBT_TAG, persistTag);
//        }
//
//        NBTTagCompound modTag = null;
//        if (player.getEntityData().hasKey("Adv"))
//            modTag = persistTag.getCompoundTag("Adv");
//        else
//        {
//            modTag = new NBTTagCompound();
//            persistTag.setTag("Adv", modTag);
//        }
//
//        if (!modTag.hasKey("hasFired") || !modTag.getBoolean("hasFired"))
//            modTag.setBoolean("hasFired", true);
//    }
}