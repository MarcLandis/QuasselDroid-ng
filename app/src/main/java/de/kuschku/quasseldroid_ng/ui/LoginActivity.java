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

package de.kuschku.quasseldroid_ng.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import de.kuschku.quasseldroid_ng.ui.chat.MainActivity;
import de.kuschku.quasseldroid_ng.ui.chat.util.ServiceHelper;
import de.kuschku.quasseldroid_ng.ui.settings.Settings;
import de.kuschku.quasseldroid_ng.ui.setup.AccountSelectActivity;
import de.kuschku.quasseldroid_ng.ui.theme.AppContext;

public class LoginActivity extends AppCompatActivity {
    private AppContext context = new AppContext();

    private boolean firstStart = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ServiceHelper.initTheme(context, this);
        context.withSettings(new Settings(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!checkReady()) {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            context.settings().preferenceLastAccount.set(data.getBundleExtra("extra").getString("account"));
            checkReady();
            firstStart = true;
        } else if (context.settings().preferenceLastAccount.get().isEmpty()) {
            finish();
        }
    }

    private boolean checkReady() {
        if (context.settings().preferenceLastAccount.get().isEmpty()) {
            Intent intent = new Intent(this, AccountSelectActivity.class);
            startActivityForResult(intent, 0);
            firstStart = true;
            return true;
        } else if (firstStart) {
            startActivity(new Intent(this, MainActivity.class));
            firstStart = false;
            return true;
        } else {
            return false;
        }
    }
}
