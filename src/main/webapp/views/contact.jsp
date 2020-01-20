<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<h1 style="color: red; text-align: center;"><u style="color: black">Contact Information</u></h1>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>
	<center><font color="red">${succMsg}</font></center>
</h2>
<h2>
	<center><font color="red">${failMsg}</font></center>
	<center><font color="red">${editMsg}</font></center>
</h2>
<form:form action="/submitInfo" modelAttribute="contactInfo" onsubmit="return (validation(this));">
	<center>
		<table style="background-color: yellow; text-align: center;border-color: black" border="1" >
		<span id="nameErr"> </span>
			<tr style="color: red">
				<form:hidden path="contactId" />
				<td style="color: red">Name</td>
				<td style="color: green"><form:input path="contactName" /></td>
				
			</tr>
			<tr>
				<td style="color: red">Email</td>
				<td style="color: green"><form:input path="contactEmail" /></td>
				<span id="emailErr"> </span>
			</tr>
			<tr>
				<td style="color: red">No</td>
				<td style="color: green"><form:input path="contactNo" /></td>
				<span id="noErr"></span>
			</tr>
			<tr>
				<td style="color: orange"><input type="submit" value="submit" /></td>
				<td style="color: orange"><input type="reset" value="reset" /></td>
			</tr>
		</table>
		
	</center>
</form:form>
<center><a href="getAllContact" style="background-color: orange">Show All Contact</a></center>

<script type="text/javascript">
	function validation(frm){
		var name=frm.contact_Name.value;
		var email=frm.contact_Email.value;
		var phone=frm.contact_No.value;
	//	alert(11);
		document.getElementById("nameErr").innerHTML="";
		document.getElementById("emailErr").innerHTML="";
		document.getElementById("noErr").innerHTML="";
		
		if(name==""){
			alert(33);
			 document.getElementById ("nameErr").innerHTML="name is mandatory";	
			 frm.name.focus();
			return false;
		}
		
		if(email==""){
			document.getElementById("emailErr").innerHTML="Email is mandatory";
			frm.email.focus();
			return false;
		}
		
		
		if(phone==""){
			document.getElementById("noErr").innerHTML="no is mandatory";
			frm.phone.focus();
			return false;
		}
		
		return true;
	}
 
</script>
