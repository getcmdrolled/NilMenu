package net.scoobis.nilmenu;

import java.util.List;
import net.minecraft.src.GuiSlot;
import net.minecraft.src.Tessellator;
import nilloader.api.NilMetadata;
import nilloader.api.NilModList;

public class GuiModListContent extends GuiSlot {

    private final GuiModList parent;
    public final List<NilMetadata> modList;
    public int currentIndex;

    public GuiModListContent(GuiModList parent) {
        super(parent.mc, (parent.width * 2 / 3) - 2, parent.height - 60, 36, parent.height - 42, 18);
        this.parent = parent;
        this.modList = NilModList.getAll();
        this.currentIndex = 0;
    }

    @Override
    public int getSize() {
        return this.modList.size();
    }

    @Override
    public void elementClicked(int arg0, boolean arg1) {
        this.currentIndex = arg0;
    }

    @Override
    public boolean isSelected(int arg0) {
        return arg0 == this.currentIndex;
    }

    @Override
    public int getContentHeight() {
        return this.getSize() * 18;
    }

    @Override
    public void drawBackground() {
        this.parent.drawDefaultBackground();
    }

    @Override
    public void drawSlot(int arg0, int arg1, int arg2, int arg3, Tessellator arg4) {
        NilMetadata mod = this.modList.get(arg0);
        String modName = mod.name;
        this.parent.drawCenteredString(this.parent.fontRenderer, modName, this.width / 2, arg2 + 1, 16777215);
    }

}
