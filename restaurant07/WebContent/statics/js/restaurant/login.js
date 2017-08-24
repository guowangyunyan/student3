
/**
 * 登录
 */
$("#submitBtn").click(function(){
	
	// 用户邮箱非空判断
	var email = $("#email").val();
	if (isEmpty(email)) {
		toastr.error("用户邮箱不能为空！");
		$("#email").focus();
		return;
	}
	
	// 用户密码非空判断
	var password = $("#password").val();
	if (isEmpty(password)) {
		toastr.error("用户密码不能为空！");
		$("#password").focus();
		return;
	}
	
	// 用户是否记住密码 1=记住，0=不记住
	var isRememberPwd = 0;
	if ($("#isRememberPwd").attr("checked")) {
		isRememberPwd = 1;
	}
	
	// 提交
	$.ajax({
		type:"post",
		url:"userServlet",
		data:{"action":"login", "email":email, "password":password, "isRememberPwd":isRememberPwd},
		success:function(data) {//data="true"
			console.log(data);
			console.log(data.resultCode);
			if (data==null) {
				toastr.error("登录失败！");
				return;
			}
			if (data.resultCode == 1 ) { // 成功
				toastr.success("登录成功！");
				// 延时一秒执行
				setTimeout(function() {
					window.location.href = "indexServlet?action=info";
				}, 1000);
			} else {
				toastr.error(data.resultMessage);
				return;
			}
		}
	});
	
	
});

