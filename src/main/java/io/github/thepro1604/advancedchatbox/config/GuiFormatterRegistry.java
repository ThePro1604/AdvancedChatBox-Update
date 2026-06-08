/*
 * Copyright (C) 2021 thepro1604
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.github.thepro1604.advancedchatbox.config;

import fi.dy.masa.malilib.util.StringUtils;
import io.github.thepro1604.advancedchatbox.registry.ChatFormatterRegistry;
import io.github.thepro1604.advancedchatcore.config.gui.widgets.WidgetListRegistryOption;
import io.github.thepro1604.advancedchatcore.config.gui.widgets.WidgetRegistryOptionEntry;
import io.github.thepro1604.advancedchatcore.gui.CoreGuiListBase;
import io.github.thepro1604.advancedchatcore.gui.buttons.BackButtonListener;
import io.github.thepro1604.advancedchatcore.gui.buttons.NamedSimpleButton;
import net.minecraft.client.gui.screens.Screen;

public class GuiFormatterRegistry extends
        CoreGuiListBase<ChatFormatterRegistry.ChatFormatterOption, WidgetRegistryOptionEntry<ChatFormatterRegistry.ChatFormatterOption>, WidgetListRegistryOption<ChatFormatterRegistry.ChatFormatterOption>> {

    public GuiFormatterRegistry(Screen parent) {
        super(10, 60);
        setParent(parent);
        this.title = StringUtils.translate("advancedchatbox.screen.formatters");
    }

    @Override
    public void initGui() {
        super.initGui();
        this.reCreateListWidget();
        int x = 10;
        int y = 30;
        this.addButton(new NamedSimpleButton(x, y, StringUtils.translate("advancedchat.gui.button.back")),
                new BackButtonListener(this));
        this.getListWidget().refreshEntries();
    }

    @Override
    protected WidgetListRegistryOption<ChatFormatterRegistry.ChatFormatterOption> createListWidget(int listX,
                                                                                                   int listY) {
        return new WidgetListRegistryOption<>(listX, listY, this.getBrowserWidth(), this.getBrowserHeight(), null,
                ChatFormatterRegistry.getInstance(), this);
    }
}
