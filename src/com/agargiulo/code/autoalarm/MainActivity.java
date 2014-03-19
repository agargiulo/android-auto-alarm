package com.agargiulo.code.autoalarm;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Fragment;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
		{
			getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment())
					.commit();
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

			TextView tv = (TextView) findViewById(R.id.main_text_view);

			final String[] EVENT_PROJECTION = new String[]
			{Calendars._ID, Calendars.ACCOUNT_NAME, Calendars.CALENDAR_DISPLAY_NAME,
					Calendars.OWNER_ACCOUNT};

			final int PROJECTION_ID_INDEX = 0;
			final int PROJECTION_ACCOUNT_NAME_INDEX = 1;
			final int PROJECTION_DISPLAY_NAME_INDEX = 2;
			final int PROJECTION_OWNER_ACCOUNT_INDEX = 3;

			Cursor cur = null;
			ContentResolver cr = getContentResolver();
			Uri uri = Calendars.CONTENT_URI;

			String selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND ("
					+ Calendars.ACCOUNT_TYPE + " = ?))";

			AccountManager acm = AccountManager.get(this.getApplicationContext());
			Account[] accounts = acm.getAccountsByType("com.google");
			for (Account ac : accounts)
			{
				if (ac != null)
				{
					String[] selectionArgs = new String[]
					{ac.name, "com.google"};

					cur = cr.query(uri, EVENT_PROJECTION, selection, selectionArgs, null);

					while (cur.moveToNext())
					{
						long calID = 0;
						String displayName = null;
						String accountName = null;
						String ownerName = null;

						calID = cur.getLong(PROJECTION_ID_INDEX);
						displayName = cur.getString(PROJECTION_DISPLAY_NAME_INDEX);
						accountName = cur.getString(PROJECTION_ACCOUNT_NAME_INDEX);
						ownerName = cur.getString(PROJECTION_OWNER_ACCOUNT_INDEX);

						tv.append("\n\n--------------------\ncalID: " + calID + "\ndisplayName: "
								+ displayName + "\naccountName: " + accountName + "\nownerName: "
								+ ownerName);

					}

				}
			}
		}
		return super.onOptionsItemSelected(item);
	}
}
