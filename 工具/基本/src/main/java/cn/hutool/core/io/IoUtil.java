package cn.hutool.core.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PushbackInputStream;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import 间.工具.流;

public class IoUtil {

	/** 数据流末尾 */
	public static final int EOF = -1;
	public static long copy(InputStream in, OutputStream out) {
		return copy(in, out, 流.中);
	}

	/**
	 * 拷贝流
	 * 
	 * @param in 输入流
	 * @param out 输出流
	 * @param bufferSize 缓存大小
	 * @return 传输的byte数
	 * @throws IORuntimeException IO异常
	 */
	public static long copy(InputStream in, OutputStream out, int bufferSize) {
		return copy(in, out, bufferSize, null);
	}

	/**
	 * 拷贝流
	 * 
	 * @param in 输入流
	 * @param out 输出流
	 * @param bufferSize 缓存大小
	 * @param streamProgress 进度条
	 * @return 传输的byte数
	 * @throws IORuntimeException IO异常
	 */
	public static long copy(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) {
		if (in == null || out == null)return 0;
		if (bufferSize <= 0) {
			bufferSize = 流.中;
		}

		byte[] buffer = new byte[bufferSize];
		long size = 0;
		if (null != streamProgress) {
			streamProgress.start();
		}
		try {
			for (int readSize = -1; (readSize = in.read(buffer)) != EOF;) {
				out.write(buffer, 0, readSize);
				size += readSize;
				out.flush();
				if (null != streamProgress) {
					streamProgress.progress(size);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (null != streamProgress) {
			streamProgress.finish();
		}
		return size;
	}

	/**
	 * 拷贝流 thanks to: https://github.com/venusdrogon/feilong-io/blob/master/src/main/java/com/feilong/io/IOWriteUtil.java<br>
	 * 本方法不会关闭流
	 * 
	 * @param in 输入流
	 * @param out 输出流
	 * @param bufferSize 缓存大小
	 * @param streamProgress 进度条
	 * @return 传输的byte数
	 * @throws IORuntimeException IO异常
	 */
	public static long copyByNIO(InputStream in, OutputStream out, int bufferSize, StreamProgress streamProgress) {
		return copy(Channels.newChannel(in), Channels.newChannel(out), bufferSize, streamProgress);
	}

	/**
	 * 拷贝文件流，使用NIO
	 * 
	 * @param in 输入
	 * @param out 输出
	 * @return 拷贝的字节数
	 * @throws IORuntimeException IO异常
	 */
	public static long copy(FileInputStream in, FileOutputStream out) {
		if (in == null || out == null)return 0;

		final FileChannel inChannel = in.getChannel();
		final FileChannel outChannel = out.getChannel();

		try {
			return inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 拷贝流，使用NIO，不会关闭流
	 * 
	 * @param in {@link ReadableByteChannel}
	 * @param out {@link WritableByteChannel}
	 * @param bufferSize 缓冲大小，如果小于等于0，使用默认
	 * @param streamProgress {@link StreamProgress}进度处理器
	 * @return 拷贝的字节数
	 * @throws IORuntimeException IO异常
	 */
	public static long copy(ReadableByteChannel in, WritableByteChannel out, int bufferSize, StreamProgress streamProgress) {
		if (in == null || out == null)return 0;

		ByteBuffer byteBuffer = ByteBuffer.allocate(bufferSize <= 0 ? 流.中 : bufferSize);
		long size = 0;
		if (null != streamProgress) {
			streamProgress.start();
		}
		try {
			while (in.read(byteBuffer) != EOF) {
				byteBuffer.flip();// 写转读
				size += out.write(byteBuffer);
				byteBuffer.clear();
				if (null != streamProgress) {
					streamProgress.progress(size);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (null != streamProgress) {
			streamProgress.finish();
		}

		return size;
	}

	public static String read(InputStream in, String charsetName) {
		FastByteArrayOutputStream out = read(in);
		return (charsetName == null|| "".equals(charsetName)) ? out.toString() : out.toString(charsetName);
	}

	/**
	 * 从流中读取内容，读取完毕后并不关闭流
	 * 
	 * @param in 输入流，读取完毕后并不关闭流
	 * @param charset 字符集
	 * @return 内容
	 * @throws IORuntimeException IO异常
	 */
	public static String read(InputStream in, Charset charset) {
		FastByteArrayOutputStream out = read(in);
		return null == charset ? out.toString() : out.toString(charset);
	}

	/**
	 * 从流中读取内容，读到输出流中
	 * 
	 * @param in 输入流
	 * @return 输出流
	 * @throws IORuntimeException IO异常
	 */
	public static FastByteArrayOutputStream read(InputStream in) {
		final FastByteArrayOutputStream out = new FastByteArrayOutputStream();
		copy(in, out);
		return out;
	}


	/**
	 * 从流中读取bytes
	 * 
	 * @param in {@link InputStream}
	 * @return bytes
	 * @throws IORuntimeException IO异常
	 */
	public static byte[] readBytes(InputStream in) {
		final FastByteArrayOutputStream out = new FastByteArrayOutputStream();
		copy(in, out);
		return out.toByteArray();
	}

	/**
	 * 读取指定长度的byte数组，不关闭流
	 * 
	 * @param in {@link InputStream}，为null返回null
	 * @param length 长度，小于等于0返回空byte数组
	 * @return bytes
	 * @throws IORuntimeException IO异常
	 */
	public static byte[] readBytes(InputStream in, int length) {
		if(null == in) {
			return null;
		}
		if(length <= 0) {
			return new byte[0];
		}
		
		byte[] b = new byte[length];
		int readLength;
		try {
			readLength = in.read(b);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (readLength > 0 && readLength < length) {
			byte[] b2 = new byte[length];
			System.arraycopy(b, 0, b2, 0, readLength);
			return b2;
		} else {
			return b;
		}
	}

	
	/**
	 * 将byte[]写到流中
	 * 
	 * @param out 输出流
	 * @param isCloseOut 写入完毕是否关闭输出流
	 * @param content 写入的内容
	 * @throws IORuntimeException IO异常
	 */
	public static void write(OutputStream out, boolean isCloseOut, byte[] content) {
		try {
			out.write(content);
		} catch (IOException e) {
		} finally {
			if (isCloseOut) {
				close(out);
			}
		}
	}
	

	public static void writeObjects(OutputStream out, boolean isCloseOut, Serializable... contents) {
		ObjectOutputStream osw = null;
		try {
			osw = out instanceof ObjectOutputStream ? (ObjectOutputStream) out : new ObjectOutputStream(out);
			for (Object content : contents) {
				if (content != null) {
					osw.writeObject(content);
					osw.flush();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (isCloseOut) {
				close(osw);
			}
		}
	}

	/**
	 * 关闭<br>
	 * 关闭失败不会抛出异常
	 * 
	 * @param closeable 被关闭的对象
	 */
	public static void close(Closeable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (Exception e) {
				// 静默关闭
			}
		}
	}

	/**
	 * 关闭<br>
	 * 关闭失败不会抛出异常
	 * 
	 * @param closeable 被关闭的对象
	 */
	public static void close(AutoCloseable closeable) {
		if (null != closeable) {
			try {
				closeable.close();
			} catch (Exception e) {
				// 静默关闭
			}
		}
	}
}
