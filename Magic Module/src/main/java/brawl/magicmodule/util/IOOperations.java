package brawl.magicmodule.util;

import brawl.magicmodule.MagicModule;
import brawl.magicmodule.MagicModuleController;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOOperations {

    public static void createNexusBlockConfig() {

        File nexusBlockFile = new File(MagicModuleController.magicAPI.getPlugin().getDataFolder(),
                "/blocks/nexusBlock.yml");

        try {
            if (!nexusBlockFile.exists()) {

                Path path = nexusBlockFile.getAbsoluteFile().toPath();

                InputStream link = (MagicModule.class.getResourceAsStream(
                        "/nexusBlock.yml"));

                Files.copy(link, path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createNexusSpellConfig() {

        File nexusSpellFile = new File(MagicModuleController.magicAPI.getPlugin().getDataFolder(),
                "/spells/nexusSpell.yml");

        try {
            if (!nexusSpellFile.exists()) {

                Path path = nexusSpellFile.getAbsoluteFile().toPath();

                InputStream link = (MagicModule.class.getResourceAsStream(
                        "/nexusSpell.yml"));

                Files.copy(link, path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}