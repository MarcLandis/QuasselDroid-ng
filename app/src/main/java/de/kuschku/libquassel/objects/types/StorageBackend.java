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

import java.util.List;
import java.util.Map;

import de.kuschku.libquassel.primitives.types.QVariant;

public class StorageBackend {
    @NonNull
    public final String DisplayName;
    @NonNull
    public final Map<String, QVariant> SetupDefaults;
    @NonNull
    public final String Description;
    @NonNull
    public final List<String> SetupKeys;

    public StorageBackend(@NonNull String displayName, @NonNull Map<String, QVariant> setupDefaults, @NonNull String description,
                          @NonNull List<String> setupKeys) {
        this.DisplayName = displayName;
        this.SetupDefaults = setupDefaults;
        this.Description = description;
        this.SetupKeys = setupKeys;
    }

    @NonNull
    @Override
    public String toString() {
        return "StorageBackend{" +
                "DisplayName='" + DisplayName + '\'' +
                ", SetupDefaults=" + SetupDefaults +
                ", Description='" + Description + '\'' +
                ", SetupKeys=" + SetupKeys +
                '}';
    }
}
