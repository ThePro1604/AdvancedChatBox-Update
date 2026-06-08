/*
 * Copyright (C) 2021 thepro1604
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.github.thepro1604.advancedchatbox.suggester;

import com.mojang.brigadier.context.StringRange;
import io.github.thepro1604.advancedchatbox.chat.AdvancedSuggestion;
import io.github.thepro1604.advancedchatbox.chat.AdvancedSuggestions;
import io.github.thepro1604.advancedchatbox.interfaces.IMessageSuggestor;
import io.github.thepro1604.advancedchatcore.util.FindType;
import io.github.thepro1604.advancedchatcore.util.SearchUtils;
import io.github.thepro1604.advancedchatcore.util.StringMatch;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Environment(EnvType.CLIENT)
public class CalculatorSuggestor implements IMessageSuggestor {
    private static final String BRACKET_REGEX = "\\[[^\\[\\]]*\\]";
    public static final String NAN = "NaN";

    @Override
    public Optional<List<AdvancedSuggestions>> suggest(String text) {
        if (!text.contains("[") || !text.contains("]")) {
            return Optional.empty();
        }
        List<StringMatch> matches = SearchUtils.findMatches(text, BRACKET_REGEX, FindType.REGEX).orElse(null);
        if (matches == null) {
            return Optional.empty();
        }
        int last = -1;
        ArrayList<AdvancedSuggestions> suggest = new ArrayList<>();
        for (StringMatch m : matches) {
            if (m.start < last || m.end - m.start < 1) {
                // Don't want overlapping matches (just in case) or too small
                continue;
            }
            last = m.end;
            String string = m.match.substring(1, m.match.length() - 1);
            Expression expression = new Expression(string);
            double val = expression.calculate();
            String message = NAN;
            if (!Double.isNaN(val)) {
                message = String.valueOf(val);
            }
            StringRange range = new StringRange(m.start, m.end);
            suggest.add(new AdvancedSuggestions(range,
                    new ArrayList<>(Collections.singleton(new AdvancedSuggestion(range, message)))));
        }
        if (suggest.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(suggest);
    }
}
