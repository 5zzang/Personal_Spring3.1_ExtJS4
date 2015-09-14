<%@page language="java" contentType="application/octet-stream; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%
String fileName = (String)request.getParameter("filename");
PrintStream printStream = new PrintStream(response.getOutputStream(), true);
File file = null;
FileInputStream fileIn = null;
ServletOutputStream sout = null;

try {
	file = new File(fileName);
	
	if (file.exists()) {
		fileIn = new FileInputStream(file);
		sout = response.getOutputStream();
		int fileSize = (int)file.length();
		byte[] b = new byte[fileSize];
		
		response.setContentLength(fileSize);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" +fileName);
		
		fileIn.read(b);
		sout.write(b, 0, fileSize);
		
		fileIn.close();
		sout.flush();
		sout.close();
	} else {
		throw new Exception(fileName + " file not found.");
	}
} catch (Exception e) {
	e.printStackTrace();
} finally {
	// java.lang.IllegalStateException: getOutputStream() has already been called for this response 오류가 나는것을 방지.
	out.clear();
	out.close();
	out = pageContext.pushBody();
			
	if (fileIn != null) fileIn.close();
	if (sout != null) sout.flush();
	if (sout != null) sout.close();
}
%>