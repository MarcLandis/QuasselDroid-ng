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

package de.kuschku.libquassel.syncables.types;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import de.kuschku.libquassel.BusProvider;
import de.kuschku.libquassel.client.QClient;
import de.kuschku.libquassel.functions.types.RpcCallFunction;
import de.kuschku.libquassel.functions.types.SyncFunction;
import de.kuschku.libquassel.primitives.types.QVariant;
import de.kuschku.libquassel.syncables.types.interfaces.QSyncableObject;
import de.kuschku.util.backports.Objects;

import static de.kuschku.util.AndroidAssert.assertNotNull;

public abstract class SyncableObject<T extends SyncableObject<T>> extends Observable implements QSyncableObject<T> {
    @Nullable
    protected BusProvider provider;
    protected QClient client;
    protected boolean initialized = false;
    @Nullable
    private String objectName;

    public void syncVar(@NonNull String methodName, @NonNull Object... params) {
        sync(methodName, params);
    }

    public void sync(@NonNull String methodName, @NonNull Object[] params) {
        assertNotNull(provider);

        provider.dispatch(new SyncFunction<>(getClassName(), getObjectName(), methodName, Arrays.asList(params)));
    }

    @NonNull
    public String getClassName() {
        return getClass().getSimpleName();
    }

    @Nullable
    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(@Nullable String objectName) {
        this.objectName = objectName;
    }

    public boolean initialized() {
        return initialized;
    }

    public void renameObject(@Nullable String objectName) {
        if (!Objects.equals(this.objectName, (objectName)))
            setObjectName(objectName);
    }

    public void smartRpc(@NonNull String procedureName, @NonNull Object... params) {
        rpc("2" + procedureName, params);
    }

    public void rpcVar(@NonNull String procedureName, @NonNull Object... params) {
        rpc(procedureName, params);
    }

    public void rpc(@NonNull String procedureName, @NonNull List<QVariant> params) {
        assertNotNull(provider);

        provider.dispatch(new RpcCallFunction(procedureName, params));
    }

    public void rpc(@NonNull String procedureName, @NonNull Object[] params) {
        rpc(procedureName, toVariantList(params));
    }

    @NonNull
    private List<QVariant> toVariantList(@NonNull Object[] params) {
        List<QVariant> list = new ArrayList<>(params.length);
        for (Object element : params) {
            list.add(new QVariant<>(element));
        }
        return list;
    }

    @CallSuper
    @Override
    public void init(@NonNull String objectName, @NonNull BusProvider provider, @NonNull QClient client) {
        this.provider = provider;
        this.objectName = objectName;
        this.client = client;
        this.initialized = true;

        Log.d("libquassel", "init: " + objectName);
    }

    public void _update() {
        if (!hasChanged()) {
            setChanged();
            notifyObservers();
        }
    }
}
