/**
 * 
 */
package com.agargiulo.code.autoalarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author agargiulo
 * 
 */
public class MiscDialogFragment extends DialogFragment
{

	public static MiscDialogFragment newInstance (Bundle args)
	{
		MiscDialogFragment frag = new MiscDialogFragment();

		frag.setArguments(args);

		return frag;
	}

	private String message, pos_msg, neg_msg, neu_msg;

	@Override
	public Dialog onCreateDialog (Bundle savedInstanceState)
	{
		message = getArguments().getString(AutoAlarm.keys.FRAG_DIAG_MESSAGE.name());
		pos_msg = getArguments().getString(AutoAlarm.keys.FRAG_DIAG_POS_MSG.name());
		neg_msg = getArguments().getString(AutoAlarm.keys.FRAG_DIAG_NEG_MSG.name());
		neu_msg = getArguments().getString(AutoAlarm.keys.FRAG_DIAG_NEU_MSG.name());

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		if (message != null)
		{
			builder.setMessage(message);
		}
		if (pos_msg != null)
		{
			builder.setPositiveButton(pos_msg, new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick (DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub

				}
			});
		}
		if (neg_msg != null)
		{
			builder.setNegativeButton(neg_msg, new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick (DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub

				}
			});
		}
		if (neu_msg != null)
		{
			builder.setNeutralButton(neu_msg, new DialogInterface.OnClickListener()
			{

				@Override
				public void onClick (DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub

				}
			});
		}
		return builder.create();
	}
}
