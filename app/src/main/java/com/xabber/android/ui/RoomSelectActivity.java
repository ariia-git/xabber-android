package com.xabber.android.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.xabber.android.R;
import com.xabber.android.data.intent.EntityIntentBuilder;
import com.xabber.android.ui.helper.BarPainter;
import com.xabber.android.ui.helper.ManagedActivity;

public class RoomSelectActivity extends ManagedActivity implements RoomSelectFragment.Listener {

    private BarPainter barPainter;

    public static Intent createIntent(Context context) {
        return new EntityIntentBuilder(context, RoomSelectActivity.class).build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_with_toolbar_and_container);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavUtils.navigateUpFromSameTask(RoomSelectActivity.this);
            }
        });
        toolbar.setTitle(getString(R.string.muc_choose_conference));

        barPainter = new BarPainter(this, toolbar);
        barPainter.setDefaultColor();

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.fragment_container, new RoomSelectFragment()).commit();
        }
    }

    @Override
    public void onAccountSelected(String account) {
        barPainter.updateWithAccountName(account);
    }
}
