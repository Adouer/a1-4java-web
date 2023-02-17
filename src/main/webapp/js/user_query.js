
	//显示或隐藏时间范围
	function showTimeRange(){
		
		var regtime = document.getElementById("regtime");  
		var timerange = document.getElementById("timerange"); 
		
		if(regtime.value=="全部时间"){
			timerange.style.visibility = "hidden";
		}else{
			timerange.style.visibility = "visible";
		}
		
	}
	
	//全选
	function selectAll(){
		
		var all = document.getElementById("all"); 
		var userids = document.getElementsByName("userids");
		
		for(var i=0; i<userids.length; i++){
			userids[i].checked = all.checked;  //checked属性   选中true  没选中false
		}
	}
	
	//反选
	function selectReverse(){
		
		var userids = document.getElementsByName("userids");
		
		for(var i=0; i<userids.length; i++){
			userids[i].checked = !userids[i].checked;
		}
		
		checkAll();
	}
	
	//检查全选的状态
	function checkAll(){
		
		var all = document.getElementById("all"); 
		var userids = document.getElementsByName("userids");
		
	    /*
        var count = 0; 
		for(var i=0; i<userids.length; i++){
			if(userids[i].checked){
				count++;
			} 
		}
		
		all.checked = (count==userids.length); */
		
		var flag = true;
		for(var i=0; i<userids.length; i++){
			if(!userids[i].checked){
				flag = false;
				break;
			} 
		}
		
		all.checked = flag;
	}	
	
	//检查选中的数量>=1
	function checkCount(){
		
	    var userids = document.getElementsByName("userids");

		var flag = false;
		for(var i=0; i<userids.length; i++){
			if(userids[i].checked){  //选中
				flag = true;
				break;
			} 
		}
		
		if(!flag){
			alert("至少要选择一个用户");
			return false;
		}
		
		
		if(!confirm("是否确定删除用户")){
			return false;
		}
		
		return true;
	}