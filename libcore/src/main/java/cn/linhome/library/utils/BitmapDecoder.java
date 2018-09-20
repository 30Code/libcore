/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.linhome.library.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;

public class BitmapDecoder
{
	private BitmapDecoder()
	{
	}

	/**
	 * 获取缩放后的本地图片
	 *
	 * @param filePath 文件路径
	 * @param width    宽
	 * @param height   高
	 * @return Bitmap
	 */
	public static Bitmap readBitmapFromFileDescriptor(String filePath, int width, int height)
	{
		try
		{
			FileInputStream fis = new FileInputStream(filePath);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
			float srcWidth = options.outWidth;
			float srcHeight = options.outHeight;
			int inSampleSize = 1;

			if (srcHeight > height || srcWidth > width)
			{
				if (srcWidth > srcHeight)
				{
					inSampleSize = Math.round(srcHeight / height);
				} else
				{
					inSampleSize = Math.round(srcWidth / width);
				}
			}

			options.inJustDecodeBounds = false;
			//避免出现内存溢出的情况，进行相应的属性设置。
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			options.inDither = true;
			options.inSampleSize = inSampleSize;

			try
			{
				// 实例化Bitmap
				return BitmapFactory.decodeFileDescriptor(fis.getFD(), null, options);
			} catch (OutOfMemoryError e)
			{
				//
			}

		} catch (Exception ex)
		{

		}
		return null;
	}
}
