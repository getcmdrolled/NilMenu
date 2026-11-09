package net.scoobis.nilmenu;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiMainMenu;
import nilloader.api.ClassRetransformer;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("com.thevoxelbox.voxelmenu.GuiMainMenuVoxelBox")
public class VoxelMainMenuTransformer extends MiniTransformer implements ClassRetransformer {

    @Patch.Method("initPanelButtons()V")
    public void patchButtons(PatchContext ctx) {
        ctx.search(
                GETSTATIC("com/thevoxelbox/voxelmenu/GuiMainMenuVoxelBox", "forgeDetected", "Z")
        ).jumpBefore();

        ctx.add(
                ALOAD(0),
                GETFIELD("com/thevoxelbox/voxelmenu/GuiMainMenuVoxelBox",
                        "buttonPanelRight", "Lcom/thevoxelbox/voxelmenu/GuiButtonPanel;"),
                LDC("Nilmods"),
                INVOKEVIRTUAL("com/thevoxelbox/voxelmenu/GuiButtonPanel",
                        "addButton", "(Ljava/lang/String;)Lcom/thevoxelbox/voxelmenu/GuiButtonMainMenu;"),
                GETFIELD("net/minecraft/src/GuiButton", "id", "I"),
                PUTSTATIC("net/scoobis/nilmenu/MainMenuTransformer$Hooks", "buttonId", "I")
        );
    }

    @Patch.Method("initPanelButtons()V")
    public void forceLiteModsButton(PatchContext ctx) {
        ctx.jumpToLastReturn();
        ctx.searchBackward(
                GETSTATIC("com/thevoxelbox/voxelmenu/GuiMainMenuVoxelBox", "forgeDetected", "Z")
        ).jumpAfter();

        ctx.add(
                POP(),
                ICONST_1()
        );
    }

    @Patch.Method("a(Lnet/minecraft/src/GuiButton;)V")
    public void patchAction(PatchContext ctx) {
        ctx.jumpToLastReturn(); // Equivalent to "TAIL" in Mixin

        ctx.add(
                ALOAD(0), // this
                ALOAD(1), // var1
                INVOKESTATIC("net/scoobis/nilmenu/MainMenuTransformer$Hooks", "onAction",
                        "(Lnet/minecraft/src/GuiMainMenu;Lnet/minecraft/src/GuiButton;)V")
        );
    }

    public static class Hooks {

        public static int buttonId = 132;

        public static void onAction(GuiMainMenu thiz, GuiButton var1) {
            if (var1.id == buttonId) {
                thiz.mc.displayGuiScreen(new GuiModList(thiz));
            }
        }

    }

}
