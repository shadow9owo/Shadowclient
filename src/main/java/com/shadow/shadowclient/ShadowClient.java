package com.shadow.shadowclient;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.Mod.EventHandler;
import java.util.Calendar;

@Mod(modid = ShadowClient.MODID, version = ShadowClient.VERSION)
public class ShadowClient {
    private boolean fullbrightEnabled = false;
    private float previousBrightness = 0.0f;
    public static final String MODID = "Shadowclient";
    public static final String VERSION = "1.0";
    private boolean temp = false;
    private boolean tmp1 = true;
    @SideOnly(Side.CLIENT)
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

    }

    @SideOnly(Side.CLIENT)
    @EventHandler
    public void init(FMLInitializationEvent event) {
        // Initialization code

        // Register the event handler
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static boolean isEventOngoing() {
        Calendar calendar = Calendar.getInstance();
        int currentMinute = calendar.get(Calendar.MINUTE);

        // Check if the current minute is within the contest duration
        if (currentMinute >= 0 && currentMinute < 20) {
            return true; // Contest is ongoing in the current hour
        }

        // Check if the current minute is within the buffer time after the contest
        if (currentMinute >= 40 && currentMinute < 60) {
            return true; // Contest is ongoing in the next hour
        }

        return false; // No ongoing contests found
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LMENU) && Keyboard.isKeyDown(Keyboard.KEY_F)) {
            if (!fullbrightEnabled) {
                enableFullbright();
            } else {
                disableFullbright();
            }
        }else if (Keyboard.isKeyDown(Keyboard.KEY_LMENU) && Keyboard.isKeyDown(Keyboard.KEY_F9) && isEventOngoing())
        {
            if (temp) {
                temp = false;
                Minecraft.getMinecraft().thePlayer.sendChatMessage("jacobs event (hypxel skyblock) is ongoing. (message sent by Shadowclient)");
            } else {
                temp = true;
            }
        }
        else if (Keyboard.isKeyDown(Keyboard.KEY_LMENU) && Keyboard.isKeyDown(Keyboard.KEY_F9) && !isEventOngoing())
        {
            if (temp) {
                temp = false;
                Minecraft.getMinecraft().thePlayer.sendChatMessage("jacobs event (hypxel skyblock) isnt ongoing. (message sent by Shadowclient)");
            } else {
                temp = true;
            }
        }
    }
    @SideOnly(Side.CLIENT)
    private void enableFullbright() {
        fullbrightEnabled = true;
        previousBrightness = Minecraft.getMinecraft().gameSettings.gammaSetting;
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1000.0f;
    }

    @SideOnly(Side.CLIENT)
    private void disableFullbright() {
        fullbrightEnabled = false;
        Minecraft.getMinecraft().gameSettings.gammaSetting = previousBrightness;
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START && event.side.isClient()) {
            if (true && Minecraft.getMinecraft().gameSettings.keyBindForward.isKeyDown()) {
                Minecraft.getMinecraft().thePlayer.setSprinting(true);
            }
        }
    }
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (tmp1)
        {
            Minecraft.getMinecraft().gameSettings.useVbo = false;
            Minecraft.getMinecraft().gameSettings.viewBobbing = false;
            tmp1 = false;
        }
    }
}