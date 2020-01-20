<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <h1 style="color: yellow;text-align: center"><u>All Contact Details</h1>
  
 <center><table border="1" style="background-color:cyan;text-align: center;">
   <thead>
   <tr>
  		<td>CcontactId</td>
  		<td>CcontactName</td>
  		<td>CcontactEmail</td>
  		<td>CcontactNumber</td>
  		<td>Action</td>
  		
   </tr>
   </thead>
   <tbody>
   		<c:forEach items="${allCntct}" var="obj" varStatus="status">
   			<tr><td>${status.index+1}</td>
   				<td>${obj.contactName}</td>
   				<td>${obj.contactEmail}</td>
   				<td>${obj.contactNo}</td>
   				<td><a href="editContact?contact_Id=${obj.contactId}">Edit</a> &nbsp; &nbsp;
   					<a href="deleteContact?activeSw=${obj.contactId}" onclick="return dltCfm()">Delete</a>
   			</tr>
   		</c:forEach>
   </tbody>
  
</table><br>
</center><br>

<center><a href="/" style="background-color: red">Add new Contacts</a></center>

<script type="text/javascript">
		function dltCfm(){
			return confirm("Are u sure,want to delete it..");
		}
</script>
  