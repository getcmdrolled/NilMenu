package net.scoobis.nilmenu;

import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.StringTranslate;
import nilloader.api.ClassRetransformer;
import nilloader.api.lib.mini.MiniTransformer;
import nilloader.api.lib.mini.PatchContext;
import nilloader.api.lib.mini.annotation.Patch;

@Patch.Class("net.minecraft.src.GuiMainMenu")
public class MainMenuTransformer extends MiniTransformer implements ClassRetransformer {

    @Patch.Method("addSingleplayerMultiplayerButtons(IILnet/minecraft/src/StringTranslate;)V")
    public void patchButtons(PatchContext ctx) {
        ctx.jumpToLastReturn(); // Equivalent to "TAIL" in Mixin

        ctx.add(
                ALOAD(0), // this
                ILOAD(1), // arg1
                ILOAD(2), // arg2
                ALOAD(3), // arg3
                INVOKESTATIC("net/scoobis/nilmenu/MainMenuTransformer$Hooks", "onButtons",
                        "(Lnet/minecraft/src/GuiMainMenu;IILnet/minecraft/src/StringTranslate;)V")
        );
    }

    @Patch.Method("actionPerformed(Lnet/minecraft/src/GuiButton;)V")
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

        public static int buttonId = 132; // some unused ID

        public static void onButtons(GuiMainMenu thiz, int arg1, int arg2, StringTranslate arg3) {
            thiz.controlList.add(new GuiButton(buttonId, thiz.width / 2 + 104, arg1 + 84, 20, 20, "M"));
        }

        public static void onAction(GuiMainMenu thiz, GuiButton var1) {
            if (var1.id == buttonId) {
                thiz.mc.displayGuiScreen(new GuiModList(thiz));
            }
        }

    }

}
