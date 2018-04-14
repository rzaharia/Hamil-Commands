package com.rzahariasmods.hamilcommands.player.data.myNBT;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils
{
    private static final String MOD_TAG_NAME = "hamil_home";

    public static NBTTagCompound getPersistenttag(EntityPlayer player)
    {
        NBTTagCompound persistTag = null;
        if (player.getEntityData().hasKey(player.PERSISTED_NBT_TAG))
            persistTag = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
        else
        {
            persistTag = new NBTTagCompound();
            player.getEntityData().setTag(player.PERSISTED_NBT_TAG, persistTag);
        }
        return persistTag;
    }

    public static NBTTagCompound getModTag(EntityPlayer player)
    {
        NBTTagCompound persistTag = getPersistenttag(player);
        NBTTagCompound modTag = persistTag.getCompoundTag(MOD_TAG_NAME);

//        if (modTag i)
//            modTag = persistTag.getCompoundTag(MOD_TAG_NAME);
//        else
        if (!persistTag.hasKey(MOD_TAG_NAME))
        {
            modTag = new NBTTagCompound();
            persistTag.setTag(MOD_TAG_NAME, modTag);
        }
        return modTag;
    }
}
