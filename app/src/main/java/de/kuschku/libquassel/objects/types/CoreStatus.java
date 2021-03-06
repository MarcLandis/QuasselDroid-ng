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

package de.kuschku.libquassel.objects.types;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class CoreStatus {
    public final boolean Configured;
    public final boolean LoginEnabled;
    public final int CoreFeatures;
    @Nullable
    public final List<StorageBackend> StorageBackends;

    public CoreStatus(boolean configured, boolean loginEnabled, int coreFeatures,
                      @Nullable List<StorageBackend> storageBackends) {
        Configured = configured;
        LoginEnabled = loginEnabled;
        CoreFeatures = coreFeatures;
        StorageBackends = storageBackends;
    }

    @NonNull
    @Override
    public String toString() {
        return "ClientInitAck{" +
                "Configured=" + Configured +
                ", LoginEnabled=" + LoginEnabled +
                ", CoreFeatures=" + CoreFeatures +
                ", StorageBackends=" + StorageBackends +
                '}';
    }
}
