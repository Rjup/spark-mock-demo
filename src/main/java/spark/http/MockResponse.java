package spark.http;

import org.eclipse.jetty.http.HttpFields;
import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;

public class MockResponse implements HttpServletResponse, Response {

	private int status = HttpStatus.OK_200;

	private ByteArrayOutputStream bodyOutput = new ByteArrayOutputStream();

	private HttpFields headers = new HttpFields();

	@Override
	public int status() {
		return status;
	}

	@Override
	public String body() {
		try {
			return bodyOutput.toString("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void addCookie(Cookie cookie) {

	}

	@Override
	public boolean containsHeader(String name) {
		return headers.containsKey(name);
	}

	@Override
	public String encodeURL(String url) {
		return null;
	}

	@Override
	public String encodeRedirectURL(String url) {
		return null;
	}

	@Override
	public String encodeUrl(String url) {
		return null;
	}

	@Override
	public String encodeRedirectUrl(String url) {
		return null;
	}

	@Override
	public void sendError(int sc, String msg) throws IOException {
this.status = sc;
	}

	@Override
	public void sendError(int sc) throws IOException {
this.status = sc;
	}

	@Override
	public void sendRedirect(String location) throws IOException {

	}

	@Override
	public void setDateHeader(String name, long date) {
		headers.putDateField(name, date);
	}

	@Override
	public void addDateHeader(String name, long date) {
		headers.addDateField(name, date);
	}

	@Override
	public void setHeader(String name, String value) {
		headers.put(name, value);
	}

	@Override
	public void addHeader(String name, String value) {
		headers.add(name, value);
	}

	@Override
	public void setIntHeader(String name, int value) {
		headers.put(name, Integer.toString(value));
	}

	@Override
	public void addIntHeader(String name, int value) {
		headers.add(name, Integer.toString(value));
	}

	@Override
	public void setStatus(int sc) {
		status = sc;
	}

	@Override
	public void setStatus(int sc, String sm) {
		status = sc;
	}

	@Override
	public int getStatus() {
		return status;
	}

	@Override
	public String getHeader(String name) {
		return headers.get(name);
	}

	@Override
	public Collection<String> getHeaders(String name) {
		Collection<String> i = headers.getValuesList(name);
		return i == null ? Collections.emptyList() : i;
	}

	@Override
	public Collection<String> getHeaderNames() {
		return headers.getFieldNamesCollection();
	}

	@Override
	public String getCharacterEncoding() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		return new ServletOutputStream() {

			@Override
			public boolean isReady() {
				return true;
			}

			@Override
			public void setWriteListener(WriteListener writeListener) {

			}

			@Override
			public void write(int b) throws IOException {
				bodyOutput.write(b);
			}
		};
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		return null;
	}

	@Override
	public void setCharacterEncoding(String charset) {

	}

	@Override
	public void setContentLength(int len) {

	}

	@Override
	public void setContentLengthLong(long len) {

	}

	@Override
	public void setContentType(String type) {

	}

	@Override
	public void setBufferSize(int size) {

	}

	@Override
	public int getBufferSize() {
		return 0;
	}

	@Override
	public void flushBuffer() throws IOException {

	}

	@Override
	public void resetBuffer() {

	}

	@Override
	public boolean isCommitted() {
		return false;
	}

	@Override
	public void reset() {

	}

	@Override
	public void setLocale(Locale loc) {

	}

	@Override
	public Locale getLocale() {
		return null;
	}
}
