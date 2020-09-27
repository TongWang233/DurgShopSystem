<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统登录</title>
<!-- 下载并引入jquery easyUI -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/themes/color.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.9.5/jquery.easyui.min.js"></script>

<style>
* { margin: 0; padding: 0; }
        html { height: 100%; }
        body { height: 100%; background: #fff url(images/backgroud.png) 50% 50% no-repeat; background-size: cover;}
        .dowebok { position: absolute; left: 50%; top: 50%; width: 430px; height: 600px; margin: -300px 0 0 -215px; border: 1px solid #fff; border-radius: 20px; overflow: hidden;}
        .logo { width: 104px; height: 104px; margin: 50px auto 80px; background: url(images/login.png) 0 0 no-repeat; }
        .form-item { position: relative; width: 360px; margin: 0 auto; padding-bottom: 30px;}
        .form-item input { width: 360px; height: 48px; padding-left: 70px; border: 1px solid #fff; border-radius: 25px; font-size: 18px; color: #fff; background-color: transparent; outline: none;}
        .form-item button { width: 360px; height: 50px; border: 0; border-radius: 25px; font-size: 18px; color: #1f6f4a; outline: none; cursor: pointer; background-color: #fff; }
        #username { background: url(images/emil.png) 20px 14px no-repeat; }
        #password { background: url(images/password.png) 23px 11px no-repeat; }
        .tip { display: none; position: absolute; left: 20px; top: 52px; font-size: 14px; color: #f50; }
        .reg-bar { width: 360px; margin: 20px auto 0; font-size: 14px; overflow: hidden;}
        .reg-bar a { color: #fff; text-decoration: none; }
        .reg-bar a:hover { text-decoration: underline; }
        .reg-bar .reg { float: left; }
        .reg-bar .forget { float: right; }
        .dowebok ::-webkit-input-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok :-moz-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok ::-moz-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}
        .dowebok :-ms-input-placeholder { font-size: 18px; line-height: 1.4; color: #fff;}

        @media screen and (max-width: 500px) {
            * { box-sizing: border-box; }
            .dowebok { position: static; width: auto; height: auto; margin: 0 30px; border: 0; border-radius: 0; }
            .logo { margin: 50px auto; }
            .form-item { width: auto; }
            .form-item input, .form-item button, .reg-bar { width: 100%; }
        }

</style>

</head>
<body>
<center>
<h1 style="margin-top:20px; color:white">
	医院中央药房药品后台管理系统
</h1>
</center>

<div class="dowebok">
		
        <div class="logo"></div>
       
       
       <form id="users_fm" method="post">
	        <div class="form-item">
	            <input id="username" name="doctorNo" type="text" autocomplete="off" placeholder="工号">
	        </div>
	        <div class="form-item">
	            <input id="password" name="password" type="password" autocomplete="off" placeholder="密码">
	        </div>
	       
	        
	        <div class="form-item"> 
	            <input name="validate" style="float:left ; width:240px;"
	                data-options="iconCls:'icon-lock',iconWidth:30,iconAlign:'left',prompt:'验证码'"  placeholder="验证码"/> 
	            <img
	                id="validateImage" style="cursor: pointer; margin: 3px; float:right;"
	                src="validatecode.jsp" alt="验证码"
	                height="40px" width="80px" onclick="resetCode()">
	        </div>
	        <br><br><br>
	        
	        <center>
	        <div class="form-item" >
	        	<a href="#" id="loginbtn" style="width: 40%; height: 35px;"
					class="easyui-linkbutton"  onclick="login()" >登陆</a>
	        
	        </div>
	        </center>
	        
	        
	    </form>
	    </div>



	<script type="text/javascript">
		function resetCode() {
			$("#validateImage").attr('src',"${pageContext.request.contextPath}/validatecode.jsp?r="
					+ new Date().getTime());
		}

		function clearForm() {
			$('#users_fm').form('reset');
		}
		function login() {
			//使用ajax进行提交，
			$('#users_fm')
					.form(
							'submit',
							{
								url : '${pageContext.request.contextPath}/sys/doctor/login',
								onSubmit : function() {
									return $(this).form('validate');
								},
								success : function(result) {
									var result = eval('(' + result + ')');
									if (!result.success) {
										$.messager.show({
											title : 'Error',
											timeout : 2000,
											msg : result.errorMsg
										});
										$('#users_fm').form('reset');
										resetCode();
										
									} else {
										$.messager.show({
											title : 'Info',
											timeout : 2000,
											msg : result.errorMsg
										});
										window.location.href = "${pageContext.request.contextPath}/pages/index.jsp";
									}
								}
							});
		}
	</script>
</body>
</html>