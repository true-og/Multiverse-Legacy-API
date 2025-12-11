package com.onarandombox.MultiverseCore;

import org.jetbrains.annotations.NotNull;
import org.mvplugins.multiverse.core.MultiverseCoreApi;
import org.mvplugins.multiverse.core.world.helpers.PlayerWorldTeleporter;

import com.onarandombox.MultiverseCore.api.Core;
import com.onarandombox.MultiverseCore.api.MVPlugin;
import com.onarandombox.MultiverseCore.api.MVWorldManager;
import com.onarandombox.MultiverseCore.destination.DestinationFactory;
import com.onarandombox.MultiverseCore.utils.AnchorManager;
import com.onarandombox.MultiverseCore.utils.WorldManager;
import com.onarandombox.legacy.plugin.MockPlugin;

import io.papermc.paper.plugin.configuration.PluginMeta;

public final class MultiverseCore extends MockPlugin implements Core, MVPlugin {

	private DestinationFactory legacyDestinationFactory;
	private WorldManager legacyWorldManager;
	private AnchorManager legacyAnchorManager;

	public MultiverseCore() {
		super("Multiverse-Core", "4.0.0", MultiverseCore.class.getName());
	}

	@Override
	public void onEnable() {
		MultiverseCoreApi api = MultiverseCoreApi.get();
		legacyDestinationFactory = new DestinationFactory(api.getDestinationsProvider());
		legacyWorldManager = new WorldManager(api.getWorldManager(), api.getServiceLocator().getService(PlayerWorldTeleporter.class));
		legacyAnchorManager = new AnchorManager(api.getAnchorManager());
	}

	@Override
	public @NotNull String getName() {
		return "Multiverse-Core";
	}

	@Override
	public DestinationFactory getDestFactory() {
		return legacyDestinationFactory;
	}

	@Override
	public MVWorldManager getMVWorldManager() {
		return legacyWorldManager;
	}

	@Override
	public AnchorManager getAnchorManager() {
		return legacyAnchorManager;
	}

	@Override
	public String dumpVersionInfo(String buffer) {
		return ""; // should be already deprecated anyways
	}

	@Override
	public MultiverseCore getCore() {
		return this;
	}

	@Override
	public void setCore(MultiverseCore core) {
		// ignore as this is already core
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