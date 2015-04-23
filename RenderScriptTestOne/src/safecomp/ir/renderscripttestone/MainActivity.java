package safecomp.ir.renderscripttestone;

import android.os.Bundle;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.RenderScript;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private Bitmap mBitmap;

	private RenderScript mRS;
	private ScriptC_myinvert mScript;
	private Allocation mInAllocation;
	private Allocation mOutAllocation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mBitmap = loadBitmap(R.drawable.lena);

		ImageView in = (ImageView) findViewById(R.id.myImg);
		in.setImageBitmap(mBitmap);

		findViewById(R.id.invertBtn).setOnClickListener(
				new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						callScript();
					}
				});

	}

	private void callScript() {
		mRS = RenderScript.create(this);
		mInAllocation = Allocation.createFromBitmap(mRS, mBitmap);
		mOutAllocation = Allocation.createTyped(mRS, mInAllocation.getType());
		mScript = new ScriptC_myinvert(mRS);
		mScript.forEach_doinvert(mInAllocation, mOutAllocation);
		mOutAllocation.copyTo(mBitmap);
	}

	private Bitmap loadBitmap(int resource) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		return BitmapFactory.decodeResource(getResources(), resource, options);
	}

}
