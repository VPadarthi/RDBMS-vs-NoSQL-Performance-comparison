<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Blog" method="post">
<h1>Welcome to blog page</h1> 
<%-- <% 
out.print(request.getAttribute("servletName").toString());
%> --%>
<input type="text" id="firstname" name="searchtext" placeholder="search blog" />
<!-- <input type="checkbox" name="Author" value="Author"> Author
  <input type="checkbox" name="Title" value="Title" > Title
  <input type="checkbox" name="Tag" value="Tag"> Tag
  <input type="checkbox" name="Blog" value="Blog_Content" > Blog Content -->
  <br>
  <%-- <input type="hidden" name="fname" value="<%out.print(request.getAttribute("servletName").toString()); %>"><br> --%>
  <input type="radio" name="Query selected" value="1"> Search in Title Contents<br>
    <input type="radio" name="Query selected" value="2"> search in Title and Blog Content<br>
    <input type="radio" name="Query selected" value="3"> Search in Title, Blog and Tag content<br>
    <input type="radio" name="Query selected" value="4"> Search in Title, Blog,Tag and Author<br>

    <span>
  
  <input type="submit"> 

</form>
</body>
</html>