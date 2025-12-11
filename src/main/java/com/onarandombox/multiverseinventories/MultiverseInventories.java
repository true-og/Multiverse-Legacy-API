package com.onarandombox.multiverseinventories;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.mvplugins.multiverse.core.command.MVCommandManager;
import org.mvplugins.multiverse.inventories.MultiverseInventoriesApi;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVPlugin;
import com.onarandombox.legacy.plugin.MockPlugin;
import com.onarandombox.multiverseinventories.profile.WorldGroupManager;

import io.papermc.paper.plugin.configuration.PluginMeta;

public final class MultiverseInventories extends MockPlugin implements MVPlugin {

	private static MultiverseInventories instance;

	private MultiverseCore core;

	public MultiverseInventories() {
		super("Multiverse-Inventories", "4.0.0", MultiverseInventories.class.getName());
	}

	public static MultiverseInventories getPlugin() {
		if (instance == null) {
			throw new IllegalStateException("MultiverseInventories has not been initialized.");
		}
		return instance;
	}

	private WorldGroupManager legacyWorldGroupManager;

	@Override
	public void onEnable() {
		core = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
		MultiverseInventoriesApi api = MultiverseInventoriesApi.get();
		legacyWorldGroupManager = new YamlWorldGroupManager(api.getServiceLocator().getService(MVCommandManager.class), api.getWorldGroupManager());
		instance = this;
	}

	@Override
	public @NotNull String getName() {
		return "Multiverse-Inventories";
	}

	/**
	 * @return The World Group manager for this plugin.
	 */
	public WorldGroupManager getGroupManager() {
		return legacyWorldGroupManager;
	}

	@Override
	public String dumpVersionInfo(String buffer) {
		return "";
	}

	@Override
	public MultiverseCore getCore() {
		return core;
	}

	@Override
	public void setCore(MultiverseCore core) {
		this.core = core;
	}

	@Override
	public int getProtocolVersion() {
		return 24;
	}

	@Override
	public @NotNull PluginMeta getPluginMeta() {
		return (PluginMeta) this.getDescription();
	}

}