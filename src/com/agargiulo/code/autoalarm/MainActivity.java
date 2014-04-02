package com.agargiulo.code.autoalarm;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity
{

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{

		public PlaceholderFragment ()
		{
		}

		@Override
		public View onCreateView (LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}
	}

	private SharedPreferences prefs;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		if (!prefs.getBoolean(AutoAlarm.prefs.SETUPCOMPLETE.name(), false))
		{
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
					.commit();
			Bundle args = new Bundle();

			args.putString(AutoAlarm.keys.FRAG_DIAG_MESSAGE.name(),
					getString(R.string.setup_message));

			args.putString(AutoAlarm.keys.FRAG_DIAG_POS_MSG.name(),
					getString(R.string.pos_button_msg));

			args.putString(AutoAlarm.keys.FRAG_DIAG_NEG_MSG.name(),
					getString(R.string.neg_button_msg));

			MiscDialogFragment frag = MiscDialogFragment.newInstance(args);

			frag.show(getFragmentManager(), "foo");
		}
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
		}
		return super.onOptionsItemSelected(item);
	}
}
