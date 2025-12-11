package com.onarandombox.MultiversePortals;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.mvplugins.multiverse.portals.MultiversePortalsApi;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiverseCore.api.MVPlugin;
import com.onarandombox.MultiversePortals.utils.PortalManager;
import com.onarandombox.legacy.plugin.MockPlugin;

import io.papermc.paper.plugin.configuration.PluginMeta;

public class MultiversePortals extends MockPlugin implements MVPlugin {

	private MultiverseCore core;
	private PortalManager legacyPortalManager;

	public MultiversePortals() {
		super("Multiverse-Portals", "4.0.0", MultiversePortals.class.getName());
	}

	@Override
	public void onEnable() {
		core = (MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
		MultiversePortalsApi api = MultiversePortalsApi.get();
		legacyPortalManager = new PortalManager(api.getPortalManager());
	}

	public PortalManager getPortalManager() {
		return legacyPortalManager;
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
	public @NotNull String getName() {
		return "Multiverse-Portals";
	}

	@Override
	public @NotNull PluginMeta getPluginMeta() {
		return (PluginMeta) this.getDescription();
	}

}