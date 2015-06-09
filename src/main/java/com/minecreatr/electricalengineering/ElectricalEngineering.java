package com.minecreatr.electricalengineering;

import com.minecreatr.electricalengineering.common.CommonProxy;
import com.minecreatr.electricalengineering.common.init.ModBlocks;
import com.minecreatr.electricalengineering.common.init.ModTiles;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Main mod class
 * This mode adds stuff that has to do with electricity and technology
 *
 * @author minecreatr
 */
@Mod(modid = Reference.MODID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class ElectricalEngineering implements Reference{

    @Mod.Instance(MODID)
    public static ElectricalEngineering instance;

    @SidedProxy(clientSide = CLIENT_PROXY, serverSide = SERVER_PROXY)
    public static CommonProxy proxy;

    public static final CreativeTabs tab = new CreativeTabs("tabElectricalEngineering") {
        @Override
        public Item getTabIconItem() {
            return Items.armor_stand;
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(this);
        ModBlocks.preInit();
        ModTiles.preInit();
    }

}
