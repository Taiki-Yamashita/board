package board.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import board.exception.IORuntimeException;

/**
 * ストリーム関係のユーティリティー
 */
public class Streamutil {

	/**
	 * input から outputにデータをコピーします。
	 *
	 * @param input
	 * @param output
	 */
	public static void copy(InputStream input, OutputStream output) {
		System.out.println(input);
		System.out.println(output);
		byte[] buffer = new byte[4096];
		try {
			for (int n = 0; -1 != (n = input.read(buffer));) {
				output.write(buffer, 0, n);
			}
		} catch (IOException e) {
			throw new IORuntimeException(e);
		}

	}

}