<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<title>Edit</title>
<style>
#box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body style="background-image:url(http://www.planwallpaper.com/static/images/10e39f13ddfb80570f3e44fb2016cb76.jpg);">

 <b>Edit Form</b>

  <div>
   <!-- 
   		Mapiranje izmedju JSP i Jave:
   		
   		U JSP formi action="update" 
   		poziva dio koda iz MainControllera:
   		
		@RequestMapping("/update")  
	 	public String updateUser(@ModelAttribute LoginBean user)
	-->
   <form:form id="box" method="post" action="update" modelAttribute="loginBean">
    <table>
     <tr>
      <td>Id :</td>
      <td><form:input path="id"
        value="${loginBean.id}" />
      </td>
     </tr>
     <tr>
      <td>Name :</td>
      <td><form:input path="name"
        value="${loginBean.name}" />
      </td>
     </tr>
     <tr>
      <td>User Id :</td>
      <td><form:input path="userid"
        value="${loginBean.userid}" />
      </td>
     </tr>
     <tr>
      <td>Pwd :</td>
      <td><form:input path="pwd"
        value="${loginBean.pwd}" />
      </td>
     </tr>
     <tr>
      <td> </td>
      <td><input type="submit" value="Save" />
      </td>
     </tr>
    </table>
   </form:form>
  </div>

</body>
</html>
