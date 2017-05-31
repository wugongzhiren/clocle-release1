package tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Looper;

public class Utils {

	/**
	 * 将bitmap转化为png图片保存在sd卡的clocle_img文件夹里
	 * 
	 * @param photoBitmap
	 * @param photoName
	 * @param path
	 */
	public static String savePhoto(Bitmap photoBitmap, String path,
			String photoName) {
		String localPath = null;
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			File photoFile = new File(path, photoName + ".png");
			FileOutputStream fileOutputStream = null;
			try {
				fileOutputStream = new FileOutputStream(photoFile);
				if (photoBitmap != null) {
					if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 50,
							fileOutputStream)) {
						localPath = photoFile.getPath();
						fileOutputStream.flush();
					}
				}
			} catch (FileNotFoundException e) {
				photoFile.delete();
				localPath = null;
				e.printStackTrace();
			} catch (IOException e) {
				photoFile.delete();
				localPath = null;
				e.printStackTrace();
			} finally {
				try {
					if (fileOutputStream != null) {
						fileOutputStream.close();
						fileOutputStream = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return localPath;
	}



	public static Bitmap toRoundBitmap(Bitmap bitmap, Uri tempUri) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		float roundPx;
		float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
		if (width <= height) {
			roundPx = width / 2;
			left = 0;
			top = 0;
			right = width;
			bottom = width;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			roundPx = height / 2;
			float clip = (width - height) / 2;
			left = clip;
			right = width - clip;
			top = 0;
			bottom = height;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}

		Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect src = new Rect((int) left, (int) top, (int) right,
				(int) bottom);
		final Rect dst = new Rect((int) dst_left, (int) dst_top,
				(int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);


		canvas.drawCircle(roundPx, roundPx, roundPx, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);

		return output;
	}

	/**
	 * 检查对象非空
	 *
	 * @param object
	 * @param message
	 * @param <T>
	 * @return
	 */
	public static <T> T checkNotNull(T object, String message) {
		if (object == null) {
			throw new NullPointerException(message);
		}
		return object;
	}
	/**
	 * 检查是否在主线程 关键检查 弹出异常
	 */
	public static void checkUiThread() {
		if (Looper.getMainLooper() != Looper.myLooper()) {
			throw new IllegalStateException(
					"Must be called from the main thread. Was: " + Thread.currentThread());
		}
	}
}
