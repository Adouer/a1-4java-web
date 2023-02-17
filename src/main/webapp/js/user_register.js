	var xhr = new XMLHttpRequest();

	//字段级校验 ---检查用户名
	function checkUsername(){
		
		//获取用户名文本框
		var username = document.getElementById("username");
		
		//获取用户名检查结果文本框
		var usernameResult = document.getElementById("usernameResult");
		
		//if(username.value.length==0){    //value表示文本框的输入的内容  length表示长度
		//if(username.value==""){   //==用于比较字符串的内容
		if(username.value.length < 5 || username.value.length > 10){
			//方式1 弹框
			//alert("用户名长度必须在5-10个字符");
			
			//方式2 利用span标签动态改变报错信息
			//改变检查结果   innerText表示文本  innerHTML表示文本+样式
			usernameResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>用户名长度必须在5-10个字符</b></font>";
			
		}else{
			
			//开始ajax操作
			usernameResult.innerHTML = "<img src='image/loading.gif'/><font color='blue'><b>正在检测用户名是否有效。。。</b></font>";
			
			xhr.open("get", "CheckUsernameServlet?username=" + encodeURI(username.value), true);
			
			xhr.send(null);
			
			xhr.onreadystatechange = checkUsernameResult;
			
		}
		
	}

	//回调函数
	function checkUsernameResult(){
		
		var usernameResult = document.getElementById("usernameResult");
		
		if(xhr.readyState==4){
			
			if(xhr.status==200){
				
				if(xhr.responseText=="yes"){
					usernameResult.innerHTML = "<img src='image/yes.gif'/><font color='green'><b>恭喜，用户名可用</b></font>";
				}else{
					usernameResult.innerHTML = "<img src='image/no.gif'/><font color='red'><b>对不起，用户名已存在</b></font>";
				}
				
			}else{
				usernameResult.innerText = "调用失败";
			}
		}
	}
	
	//字段级校验 ---检查用户密码
	function checkPassword(){
	
	}
	
	
	//字段级校验 ---检查两次密码是否一致
	function checkPassword2(){
		
		var password = document.getElementById("password");
		var password2 = document.getElementById("password2");
		
		if(password.value!=password2.value){
			password2Result.innerHTML = "<img src='image/no.gif'/><font color='red'><b>两次密码不一致</b></font>";
		}else{
			password2Result.innerHTML = "";    //清空提示信息
		}
	}
	
	//表单级校验   onsubmit事件表示只有代码返回true才允许提交，返回false则不允许提交
	function checkData(){

		var username = document.getElementById("username");
		var password = document.getElementById("password");
		var password2 = document.getElementById("password2");
		
		//检查用户名
		if(username.value.length < 5 || username.value.length > 10){
			alert("用户名长度必须在5-10个字符");
			return false;
		}
		
		//检查密码
		if(password.value.length==0){
			alert("密码不能为空");
			return false;
		}
		
		//检查两次密码是否一致
		if(password.value!=password2.value){
			alert("两次密码不一致");
			return false;
		}
		
		//检查自定义头像
		
		var photo = document.getElementById("photo");
		var file = document.getElementById("file");
		
		if(photo.checked){
			if(file.value==""){
				alert("必须选择自定义头像");
				return false;
			}
		}
		
		return true;
		
	}
	
	//显示或隐藏文件输入框
	function changePhoto(){
		
		var photo = document.getElementById("photo");
		var file = document.getElementById("file");
		
		if(photo.checked){
			file.style.visibility = "visible";
		}else{
			file.style.visibility = "hidden";
		}
		
	}