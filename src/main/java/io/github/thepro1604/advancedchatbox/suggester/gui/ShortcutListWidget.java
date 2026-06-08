/*
 * Copyright (C) 2021 thepro1604
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.github.thepro1604.advancedchatbox.suggester.gui;

import fi.dy.masa.malilib.gui.interfaces.ISelectionListener;
import io.github.thepro1604.advancedchatbox.suggester.ShortcutSuggestor;
import io.github.thepro1604.advancedchatcore.gui.WidgetConfigList;
import net.minecraft.client.gui.screens.Screen;

import javax.annotation.Nullable;
import java.util.Collection;

public class ShortcutListWidget extends WidgetConfigList<ShortcutSuggestor.Shortcut, ShortcutEntryListWidget> {
    public final ShortcutSuggestor suggestor;

    public ShortcutListWidget(int x, int y, int width, int height,
                              @Nullable ISelectionListener<ShortcutSuggestor.Shortcut> selectionListener, ShortcutSuggestor parent,
                              Screen screen) {
        super(x, y, width, height, selectionListener, screen);
        this.suggestor = parent;
    }

    @Override
    protected ShortcutEntryListWidget createListEntryWidget(int x, int y, int listIndex, boolean isOdd,
                                                            ShortcutSuggestor.Shortcut entry) {
        return new ShortcutEntryListWidget(x, y, this.browserEntryWidth, this.getBrowserEntryHeightFor(entry), isOdd,
                entry, listIndex, this);
    }

    @Override
    protected Collection<ShortcutSuggestor.Shortcut> getAllEntries() {
        return suggestor.getShortcuts();
    }
}
