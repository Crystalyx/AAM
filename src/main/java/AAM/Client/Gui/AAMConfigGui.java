package AAM.Client.Gui;

import java.io.File;

import AAM.Core.AAMConfig;
import AAM.Core.AAMCore;
import cpw.mods.fml.client.config.GuiConfig;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

public class AAMConfigGui extends GuiConfig
{

	public AAMConfigGui(GuiConfig parent)
	{
		super(parent, new ConfigElement(new AAMConfig(new Configuration(new File(AAMCore.cfgDir, "AlchemyAndMagicReloaded.cfg"))).cfg.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), AAMCore.name, false, false,
				GuiConfig.getAbridgedConfigPath(new Configuration(new File(AAMCore.cfgDir, "AlchemyAndMagicReloaded.cfg")).toString()));
		;

	}

}
