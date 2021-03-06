/*
 * QuasselDroid - Quassel client for Android
 * Copyright (C) 2016 Janne Koschinski
 * Copyright (C) 2016 Ken Børge Viktil
 * Copyright (C) 2016 Magnus Fjell
 * Copyright (C) 2016 Martin Sandsmark <martin.sandsmark@kde.org>
 *
 * This program is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.kuschku.libquassel.syncables.serializers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;

import de.kuschku.libquassel.functions.types.PackedFunction;
import de.kuschku.libquassel.functions.types.SerializedFunction;
import de.kuschku.libquassel.functions.types.UnpackedFunction;
import de.kuschku.libquassel.objects.serializers.ObjectSerializer;
import de.kuschku.libquassel.primitives.types.QVariant;
import de.kuschku.libquassel.syncables.types.impl.IgnoreListManager;

import static de.kuschku.util.AndroidAssert.assertNotNull;

@SuppressWarnings("unchecked")
public class IgnoreListManagerSerializer implements ObjectSerializer<IgnoreListManager> {
    @NonNull
    private static final IgnoreListManagerSerializer serializer = new IgnoreListManagerSerializer();

    private IgnoreListManagerSerializer() {

    }

    @NonNull
    public static IgnoreListManagerSerializer get() {
        return serializer;
    }

    @Nullable
    @Override
    public QVariant<Map<String, QVariant>> toVariantMap(@NonNull IgnoreListManager data) {
        // FIXME: IMPLEMENT
        throw new IllegalArgumentException();
    }

    @NonNull
    @Override
    public IgnoreListManager fromDatastream(@NonNull Map<String, QVariant> map) {
        return fromLegacy(map);
    }

    @NonNull
    @Override
    public IgnoreListManager fromLegacy(@NonNull Map<String, QVariant> map) {
        Map<String, QVariant> internalMap = (Map<String, QVariant>) map.get("IgnoreList").data;
        assertNotNull(internalMap);
        return new IgnoreListManager(
                (List<Integer>) internalMap.get("scope").data,
                (List<Integer>) internalMap.get("ignoreType").data,
                (List<Boolean>) internalMap.get("isActive").data,
                (List<String>) internalMap.get("scopeRule").data,
                (List<Boolean>) internalMap.get("isRegEx").data,
                (List<Integer>) internalMap.get("strictness").data,
                (List<String>) internalMap.get("ignoreRule").data
        );
    }

    @Nullable
    @Override
    public IgnoreListManager from(@NonNull SerializedFunction function) {
        if (function instanceof PackedFunction)
            return fromLegacy(((PackedFunction) function).getData());
        else if (function instanceof UnpackedFunction)
            return fromDatastream(((UnpackedFunction) function).getData());
        else throw new IllegalArgumentException();
    }
}
