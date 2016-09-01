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
					<div class="formtext">您好，请在此处登记信访信息！</div>
					<ul class="forminfo">
						<li><label>投訴形式<b>*</b></label>
							<div class="usercity">
								<div class="cityleft">
									<s:select list="#request.tsxs_list" class="select2" listKey="id" listValue="itemName"
										name="caseTypeId">

									</s:select>
								</div>
								<label>投诉人姓名<b>*</b></label>
								<div class="cityright">
									<s:textfield name="senderName" type="text" class="scinput" />
								</div>
							</div></li>


						<li><label>投诉人电话<b>*</b></label>
							<div class="usercity">
								<div class="cityleft">
									<s:textfield name="senderPhone" type="text" class="scinput" />
									&nbsp;&nbsp;&nbsp;
								</div>
								<label>投诉日期<b>*</b></label>
								<div class="cityright">
									<s:textfield name="sendDate" type="text" class="scinput"
										onclick="WdatePicker('yyyyMMdd')" />
								</div>
							</div></li>

						<li><label >被投诉单位<b>*</b></label>
						<s:textfield name="depart" type="text" class="dfinput"
								placeholder="请填写被投诉单位名称"  /></li>
						<li><label >单位电话<b>*</b></label>
						<s:textfield name="departPhone" type="text" class="dfinput"
								placeholder="请填写被投诉单位电话"  /></li>
						<li><label>投诉描述<b>*</b></label> <textarea name="tousuText"
								 cols="" rows="" class="textinput"></textarea></li>
						<li><label>&nbsp;</label>
						<s:submit   class="btn" value="确认保存" /></li>



					</ul>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>