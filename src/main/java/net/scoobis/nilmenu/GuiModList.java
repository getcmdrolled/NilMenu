package net.scoobis.nilmenu;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiSmallButton;
import net.minecraft.src.StringTranslate;
import nilloader.api.NilMetadata;

public class GuiModList extends GuiScreen {

    public final GuiScreen parentScreen;
    public GuiModListContent modListContent;

    public GuiModList(GuiScreen var1) {
        this.parentScreen = var1;
    }

    @Override
    public void initGui() {
        this.modListContent = new GuiModListContent(this);
        StringTranslate translate = StringTranslate.getInstance();
        this.controlList.add(new GuiSmallButton(0, this.width / 2 - 75, this.height - 38, translate.translateKey("gui.done")));
    }

    @Override
    public void drawScreen(int var1, int var2, float var3) {
        this.modListContent.drawScreen(var1, var2, var3);
        NilMetadata mod = this.modListContent.modList.get(this.modListContent.currentIndex);
        this.drawCenteredString(this.fontRenderer, "Mods", this.width / 2, 5, 16777215);
        this.drawCenteredString(this.fontRenderer, "Mod Select", (this.width - (this.width / 3 + 2)) / 2, 23, 16777215);
        this.drawCenteredString(this.fontRenderer, "Mod Info", this.width - (this.width / 6), 23, 16777215);

        this.drawString(this.fontRenderer, "Name: " + mod.name, this.width - (this.width / 3) + 4, 50, 16777215);
        this.drawString(this.fontRenderer, "Mod ID: " + mod.id, this.width - (this.width / 3) + 4, 68, 16777215);
        this.drawString(this.fontRenderer, "Author: " + mod.authors, this.width - (this.width / 3) + 4, 86, 16777215);
        this.drawString(this.fontRenderer, "Version: " + mod.version, this.width - (this.width / 3) + 4, 104, 16777215);
        this.fontRenderer.drawSplitString("Description: " + mod.description, this.width - (this.width / 3) + 4,
                122, (this.width / 3), 16777215);

        super.drawScreen(var1, var2, var3);
    }

    @Override
    public void actionPerformed(GuiButton var1) {
        if (var1.enabled) {
            switch (var1.id) {
            case 0:
                this.mc.displayGuiScreen(this.parentScreen);
                break;
            default:
                break;
            }
        }
    }

}