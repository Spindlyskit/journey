package me.spindlyskit.journey.ui.powersmenu;

import me.spindlyskit.journey.network.ClientServerChannels;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;

/**
 * Stores data about the powers menu
 */
public class PowersMenuOptions {
    private boolean open = false;
    private byte tab = 0;

    private boolean godmode = false;
    private boolean noHunger = false;

    /**
     * Store the options on the server
     */
    @Environment(EnvType.CLIENT)
    protected void sendToServer() {
        CompoundTag tag = new CompoundTag();
        serialize(tag);

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeCompoundTag(tag);
        ClientPlayNetworking.send(ClientServerChannels.SET_POWERS_MENU_OPTIONS, buf);
    }

    /**
     * Store options in a compound tab
     */
    public CompoundTag serialize(CompoundTag tag) {
        tag.putBoolean("open", isOpen());
        tag.putByte("tab", getTab());

        tag.putBoolean("godmode", isGodmode());
        tag.putBoolean("hunger", isNoHunger());

        return tag;
    }

    /**
     * Load options from a compound tag
     */
    public void deserialize(CompoundTag tag) {
        setOpen(tag.getBoolean("open"));
        setTab(tag.getByte("tab"));

        setGodmode(tag.getBoolean("godmode"));
        setNoHunger(tag.getBoolean("hunger"));
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public byte getTab() {
        return tab;
    }

    public void setTab(byte tab) {
        this.tab = tab;
    }

    public void setGodmode(boolean godmode) {
        this.godmode = godmode;
    }

    public boolean isGodmode() {
        return godmode;
    }

    public void setNoHunger(boolean noHunger) {
        this.noHunger = noHunger;
    }

    public boolean isNoHunger() {
        return noHunger;
    }
}
