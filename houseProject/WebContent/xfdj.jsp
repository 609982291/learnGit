<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<!-- 信访登记 -->
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="editor/kindeditor.js"></script>


<script type="text/javascript">
	KE.show({
		id : 'content7',
		cssPath : './index.css'
	});
</script>

<script type="text/javascript">
	$(document).ready(function(e) {
		$(".select1").uedSelect({
			width : 345
		});
		$(".select2").uedSelect({
			width : 167
		});
		$(".select3").uedSelect({
			width : 100
		});
	});
	//检查数据是否填写完整
	function checkData(){
		var caseType = document.getElementById("xfdj-save_caseTypeId");//投诉形式
		var caseTypeId = caseType.options[caseType.selectedIndex].value;
		var userName="",userPhone="",comDate="",comName="",comPhone="",caseContent="";
		userName =  document.getElementById("xfdj-save_caseUserName").value;//获取投诉人姓名
		userPhone = document.getElementById("xfdj-save_caseUserPhone").value;//获取投诉人电话
		comDate = document.getElementById("xfdj-save_complaintDate").value;//投诉日期
		comName = document.getElementById("xfdj-save_companyName").value;//投诉单位
		comPhone = document.getElementById("xfdj-save_companyPhone").value;//投诉单位电话
		caseContent = document.getElementById("xfdj-save_caseContent").value;//投诉内容;
		if(caseTypeId==0){
			alert("请选择投诉形式");return false;
		}
		if(userName==""||userName==null){
			alert("请填写投诉人姓名");return false;
		}
		if(userPhone==""||userPhone==null){
			alert("请填写投诉人电话");return false;
		}
		if(comDate==""||comDate==null){
			alert("请填写投诉日期");return false;
		}
		if(comName==""||comName==null){
			alert("请填写被投诉单位");return false;
		}
		if(comPhone==""||comPhone==null){
			alert("请填写被投诉单位电话");return false;
		}
		if(caseContent==""||caseContent==null){
			alert("请填写投诉描述");return false;
		}
		alert("添加成功");return true;
	}
</script>
<script src="js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<s:form action="xfdj-save" method="post" theme="simple">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li><a href="#">首页</a></li>
				<li><a href="#">信访登记</a></li>
			</ul>
		</div>
		<div class="formbody">
			<div id="usual1" class="usual">

				<div id="tab1" class="tabson">
					<div class="formtext">您好，请在此处登记信访信息！<s:hidden name="id"></s:hidden></div>
					<ul class="forminfo">
						<li><label>投訴形式<b>*</b></label>
							<div class="usercity">
								<div class="cityleft">
									<s:select name="caseTypeId" list="#request.tsxsList" class="select2" listKey="id" listValue="itemName">
									</s:select>
								</div>
								<label>投诉人姓名<b>*</b></label>
								<div class="cityright">
									<s:textfield name="caseUserName"   class="scinput" />
								</div>
							</div></li>
						<li><label>投诉人电话<b>*</b></label>
							<div class="usercity">
								<div class="cityleft">
									<s:textfield name="caseUserPhone" type="text" class="scinput" />
									&nbsp;&nbsp;&nbsp;
								</div>
								<label>投诉日期<b>*</b></label>
								<div class="cityright">
									<s:textfield name="complaintDate" type="text" class="scinput"
										onclick="WdatePicker('yyyyMMdd')" />
								</div>
							</div></li>
                        <li>
                        </li>
						<li><label>被投诉单位<b>*</b></label>
						<s:textfield name="companyName" type="text" class="dfinput"
								placeholder="请填写被投诉单位名称"  /></li>
						<li><label >单位电话<b>*</b></label>
						<s:textfield name="companyPhone" type="text" class="dfinput"
								placeholder="请填写被投诉单位电话"  /></li>
						<li><label>投诉描述<b>*</b></label> <s:textarea name="caseContent" id="xfdj-save_caseContent"
								 cols="" rows="" class="textinput"></s:textarea></li>
						<li><label>&nbsp;</label>
						<s:submit  class="btn" value="确认保存" onclick="return checkData()"/>		
								
						</li>
					</ul>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>