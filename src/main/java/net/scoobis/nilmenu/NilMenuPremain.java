package net.scoobis.nilmenu;

import nilloader.api.ClassTransformer;
import nilloader.api.NilLogger;

public class NilMenuPremain implements Runnable {

    public static final NilLogger log = NilLogger.get("NilMenu");

    @Override
    public void run() {
        ClassTransformer.register(new MainMenuTransformer());
        ClassTransformer.register(new VoxelMainMenuTransformer());

        log.info("Successfully registered NilMenu hooks!");
    }

}
